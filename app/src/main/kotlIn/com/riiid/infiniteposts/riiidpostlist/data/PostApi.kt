package com.riiid.infiniteposts.riiidpostlist.data

import com.riiid.infiniteposts.riiidpostlist.data.model.Comment
import com.riiid.infiniteposts.riiidpostlist.data.model.Post
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableFromArray
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApi {

    @GET("posts?")
    fun getPosts(@Query("_start") start: Int, @Query("_limit") limit: Int): Observable<List<Post>>

    @GET("posts/{postId}/comments")
    fun getComments(@Path("postId") postId: Int): Observable<List<Comment>>
}