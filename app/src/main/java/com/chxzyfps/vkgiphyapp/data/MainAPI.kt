package com.chxzyfps.vkgiphyapp.data

import com.chxzyfps.vkgiphyapp.utils.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MainAPI {
    @GET("trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("limit") limit: Int = 25
    ): GifList

    @GET("search")
    suspend fun getSearchGifs(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("q") query: String,
        @Query("limit") limit: Int = 25

    ) : GifList
}

