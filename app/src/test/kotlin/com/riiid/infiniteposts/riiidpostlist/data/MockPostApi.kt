package com.riiid.infiniteposts.riiidpostlist.data

import com.riiid.infiniteposts.riiidpostlist.data.model.Post
import io.reactivex.Observable

class MockPostApi: PostApi {

    val postList = ArrayList<Post>()
    override fun getPosts(start: Int, limit: Int): Observable<List<Post>> {

        for (index in 0 until 10) {
            postList.add(Post(index, index, "testTitle $index", "testMessage $index"))
        }

        return Observable.just(postList)
    }

}