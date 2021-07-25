package com.ishwar_arcore.saveo.repository

import com.ishwar_arcore.saveo.data.model.MovieResponseItem
import com.ishwar_arcore.saveo.data.networking.ApiService
import com.ishwar_arcore.saveo.data.networking.Resource
import com.ishwar_arcore.saveo.data.networking.ResponseHandler
import com.ishwar_arcore.saveo.data.networking.RetrofitClient

class MovieRepository {

    private val apiService = RetrofitClient.getInstance().create(ApiService::class.java)

    private val responseHandler = ResponseHandler()

    suspend fun getMoviesList(page: Int): Resource<List<MovieResponseItem>> {
        val result = apiService.getNews(page)
        return responseHandler.handleSuccess(result)
    }

}