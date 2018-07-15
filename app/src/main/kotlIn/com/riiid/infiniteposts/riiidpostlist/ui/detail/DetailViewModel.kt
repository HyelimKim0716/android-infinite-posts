package com.riiid.infiniteposts.riiidpostlist.ui.detail

import android.util.Log
import com.riiid.infiniteposts.riiidpostlist.data.PostApi
import com.riiid.infiniteposts.riiidpostlist.data.model.Comment
import com.riiid.infiniteposts.riiidpostlist.data.model.Post
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class DetailViewModel(private val postApi: PostApi) {
    val tag = "detailViewModel"
    var post: Post ?= null

    val detailViewEventSender = PublishSubject.create<Pair<DetailViewEvent, Any>>().apply { subscribeOn(Schedulers.io()) }
    val commentList = ArrayList<Comment>()

    fun loadComments() {

        post?.let {
            postApi.getComments(it.id)
                    .subscribe({
                        commentList.addAll(it)
                    }, {
                        it.printStackTrace()
                        Log.e(tag, "Get comments error: ${it.message}")
                    }, {
                        Log.e(tag, "Get comments finished")
                        detailViewEventSender.onNext(DetailViewEvent.REFRESH_COMMENT_LIST to 0)
                    })

        }
    }
}