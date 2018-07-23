package com.riiid.infiniteposts.riiidpostlist.data.api

import com.riiid.infiniteposts.riiidpostlist.data.model.ServerPhoto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApi {

    @GET("photos?")
    fun getPhotos(@Query("_start") start: Int, @Query("_limit") limit: Int): Observable<List<ServerPhoto>>

}