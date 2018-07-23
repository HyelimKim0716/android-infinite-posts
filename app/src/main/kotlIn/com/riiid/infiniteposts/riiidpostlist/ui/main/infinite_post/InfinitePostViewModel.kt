package com.riiid.infiniteposts.riiidpostlist.ui.main.infinite_post

import com.riiid.infiniteposts.riiidpostlist.base.PostRepository
import com.riiid.infiniteposts.riiidpostlist.common.LogMgr
import com.riiid.infiniteposts.riiidpostlist.data.api.PhotoApi
import com.riiid.infiniteposts.riiidpostlist.data.api.PostApi
import com.riiid.infiniteposts.riiidpostlist.data.model.PostItem
import com.riiid.infiniteposts.riiidpostlist.data.model.ServerPhoto
import com.riiid.infiniteposts.riiidpostlist.data.model.ServerPost
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class InfinitePostViewModel(private val postApi: PostApi, private val photoApi: PhotoApi, private val postRepository: PostRepository) {

    val infinitePostViewEventSender
            = PublishSubject.create<Pair<InfinitePostViewEvent, Any>>().apply { subscribeOn(Schedulers.io()) }

    val serverPostList = ArrayList<ServerPost>()
    val serverPhotoList = ArrayList<ServerPhoto>()

    val postList = ArrayList<PostItem>()

    var isGettingPostData = false
    var isGettingPhotoData = false

    fun sendInfinitePostViewEvent(viewEvent: InfinitePostViewEvent, data: Any) {
        infinitePostViewEventSender.onNext(viewEvent to data)
    }

    fun loadPosts() {
        serverPostList.clear()
        serverPhotoList.clear()

        postApi.getPosts(0, 20)
                .subscribe({
                    LogMgr.d("postApi : $it")
                    serverPostList.addAll(it)
                    serverPostList.sort()
                }, {
                    it.printStackTrace()
                    LogMgr.e("Get Posts Error message ${it.message}")
                }, {
                    LogMgr.d("Complete getting posts, serverPostList size : ${serverPostList.size}")

                    isGettingPostData = true

                    if (isGettingPhotoData)
                        setPostList()
                })

        photoApi.getPhotos(0, 20)
                .subscribe({
                    it.forEach {
                        LogMgr.d("photoApi thumbnail : ${it.albumId}, ${it.url}, ${it.thumbnailUrl}")
                    }
                    serverPhotoList.addAll(it)
                }, {
                    it.printStackTrace()
                    LogMgr.e("Get Photo Error message ${it.message}")
                }, {

                    isGettingPhotoData = true

                    if (isGettingPostData)
                        setPostList()

                    LogMgr.d("Complete getting photo, serverPhotoList size : ${serverPhotoList.size}")
                })
    }

    private fun setPostList() {
        serverPostList.forEachIndexed { index, serverPost ->

            LogMgr.d("setPostList item, index: $index, userId: ${serverPost.userId}, id: ${serverPost.id}, title: ${serverPost.title}, body: ${serverPost.body}, thumbnail: ${serverPhotoList[index].thumbnailUrl}")

            val postItem = PostItem().apply {
                userId = serverPost.userId
                id = serverPost.id
                title = serverPost.title
                body = serverPost.body
                thumbnailUrl = serverPhotoList[index].thumbnailUrl
            }
            postList.add(postItem)
        }

        isGettingPostData = false
        isGettingPhotoData = false
        sendInfinitePostViewEvent(InfinitePostViewEvent.REFRESH_POST_LIST, 0)
    }
}