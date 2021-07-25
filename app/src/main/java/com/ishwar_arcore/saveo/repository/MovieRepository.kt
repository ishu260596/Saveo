package com.ishwar_arcore.saveo.repository

import com.ishwar_arcore.saveo.data.model.ResponseMovieApi
import com.ishwar_arcore.saveo.data.model.ResponseMovieApiItem
import com.ishwar_arcore.saveo.data.networking.ApiService
import com.ishwar_arcore.saveo.data.networking.Resource
import com.ishwar_arcore.saveo.data.networking.ResponseHandler
import com.ishwar_arcore.saveo.data.networking.RetrofitClient

class MovieRepository {

    private val apiService = RetrofitClient.getInstance().create(ApiService::class.java)

    val responseHandler = ResponseHandler()

    suspend fun getMoviesList(category: String): Resource<List<ResponseMovieApiItem>> {
        val result = apiService.getNews(category)
        return responseHandler.handleSuccess(result)
    }

}