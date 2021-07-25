package com.ishwar_arcore.saveo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ishwar_arcore.saveo.data.model.MovieResponseItem
import com.ishwar_arcore.saveo.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val movieRepo: MovieRepository) : ViewModel() {

    fun getMovies(page: Int): LiveData<List<MovieResponseItem>> {
        return liveData(Dispatchers.IO) {
            val result = movieRepo.getMoviesList(page)
            emit(result.data!!)
        }
    }

}