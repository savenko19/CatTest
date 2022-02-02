package com.example.cattest.data.remote

import com.example.cattest.data.remote.response.CatResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsApi {
    @GET("v1/images/search")
    fun fetchCats(@Query("limit") limit: Int = 12): Single<List<CatResponse>>


    @GET("v1/images/search")
    fun fetchCatsPage(
        @Query("page") page: Int,
        @Query("limit") size: Int
    ): Single<List<CatResponse>>

}