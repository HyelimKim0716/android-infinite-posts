package com.riiid.infiniteposts.riiidpostlist.data.api

import com.riiid.infiniteposts.riiidpostlist.data.model.Comment
import com.riiid.infiniteposts.riiidpostlist.data.model.ServerPost
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApi {

    @GET("posts?")
    fun getPosts(@Query("_start") start: Int, @Query("_limit") limit: Int): Observable<List<ServerPost>>

    @GET("posts/{postId}/comments")
    fun getComments(@Path("postId") postId: Int): Observable<List<Comment>>

}