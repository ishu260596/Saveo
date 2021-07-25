package com.ishwar_arcore.saveo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ishwar_arcore.saveo.data.model.MovieResponseItem
import com.ishwar_arcore.saveo.data.networking.ApiService
import com.ishwar_arcore.saveo.data.networking.RetrofitClient
import com.ishwar_arcore.saveo.utils.TAG
import retrofit2.Call
import retrofit2.Response

class MovieRepository {

    private var movieList: MutableLiveData<List<MovieResponseItem>> = MutableLiveData()
    private var moviePagingList: MutableLiveData<List<MovieResponseItem>> = MutableLiveData()

    fun getMovieList(): MutableLiveData<List<MovieResponseItem>> {
        return movieList
    }

    fun getMoviePagingList(): MutableLiveData<List<MovieResponseItem>> {
        return moviePagingList
    }

    /**
     * calling API
     * **/
    fun getMoviesListApi(page: Int) {
        val apiService = RetrofitClient.getRetrofitInstance()?.create(ApiService::class.java)
        apiService?.getMovies(page)?.enqueue(object : retrofit2.Callback<List<MovieResponseItem>> {
            override fun onResponse(
                call: Call<List<MovieResponseItem>>,
                response: Response<List<MovieResponseItem>>
            ) {
                Log.d(TAG, "Success in fetching movie")
                val list = response.body()
                if (list != null) {
                    movieList.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<MovieResponseItem>>, t: Throwable) {
                Log.d(TAG, "Error in fetching movie", t)
            }
        })

    }

    /**
     * calling API
     * **/
    fun getMoviesPaginationListApi(page: Int) {
        val apiService = RetrofitClient.getRetrofitInstance()?.create(ApiService::class.java)
        apiService?.getMovies(page)?.enqueue(object : retrofit2.Callback<List<MovieResponseItem>> {
            override fun onResponse(
                call: Call<List<MovieResponseItem>>,
                response: Response<List<MovieResponseItem>>
            ) {
                Log.d(TAG, "Success in fetching movie")
                val list = response.body()
                if (list != null) {
                    moviePagingList.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<MovieResponseItem>>, t: Throwable) {
                Log.d(TAG, "Error in fetching movie", t)
            }
        })

    }
}