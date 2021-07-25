package com.ishwar_arcore.saveo.data.networking

import com.ishwar_arcore.saveo.data.model.MovieResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("shows")
     fun getMovies(@Query("page") page: Int): Call<List<MovieResponseItem>>
}