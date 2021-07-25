package com.ishwar_arcore.saveo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ishwar_arcore.saveo.data.model.ResponseMovieApiItem
import com.ishwar_arcore.saveo.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val movieRepo: MovieRepository) : ViewModel() {

    fun newsFromApi(query: String): LiveData<List<ResponseMovieApiItem>> {
        return liveData(Dispatchers.IO) {
            val result = movieRepo.getMoviesList(query)
            emit(result.data!!)
        }
    }

}