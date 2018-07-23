package com.riiid.infiniteposts.riiidpostlist.data

import com.riiid.infiniteposts.riiidpostlist.data.api.PostApi
import com.riiid.infiniteposts.riiidpostlist.data.model.ServerPost
import io.reactivex.Observable

class MockPostApi: PostApi {

    val postList = ArrayList<ServerPost>()
    override fun getPosts(start: Int, limit: Int): Observable<List<ServerPost>> {

        for (index in 0 until 10) {
            postList.add(ServerPost(index, index, "testTitle $index", "testMessage $index"))
        }

        return Observable.just(postList)
    }

}