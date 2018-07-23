package com.riiid.infiniteposts.riiidpostlist.ui.main

import android.util.Log
import com.riiid.infiniteposts.riiidpostlist.data.api.PhotoApi
import com.riiid.infiniteposts.riiidpostlist.data.api.PostApi
import com.riiid.infiniteposts.riiidpostlist.data.model.PostItem
import com.riiid.infiniteposts.riiidpostlist.data.model.ServerPost
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class MainViewModel(val postApi: PostApi) {
    val tag = "MainViewModel"
    val title = "Posts"

    val mainViewEventSender = PublishSubject.create<Pair<MainViewEvent, Any>>().apply {
        subscribeOn(Schedulers.io())
    }

    val postList = ArrayList<ServerPost>()

    private fun sendViewEvent(mainViewEvent: MainViewEvent, data: Any) {
        mainViewEventSender.onNext(mainViewEvent to data)
    }


    fun loadPosts() {
        postApi.getPosts(0, 20)
                .subscribe({
//                    postList.addAll(it)
                }, {
                    it.printStackTrace()
                    Log.e(tag, "Get Posts Error message ${it.message}")
                }, {
                    Log.e(tag, "Complete getting posts, serverPost list size : ${postList.size}")
                })


    }
}