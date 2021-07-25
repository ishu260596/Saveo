package com.ishwar_arcore.saveo.data.networking

import com.ishwar_arcore.saveo.data.model.ResponseMovieApiItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/shows")
    suspend fun getNews(@Query("q") name: String): List<ResponseMovieApiItem>
}