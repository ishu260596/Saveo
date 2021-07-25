package com.ishwar_arcore.saveo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ishwar_arcore.saveo.data.model.MovieResponseItem
import com.ishwar_arcore.saveo.repository.MovieRepository

class MovieViewModel(private val movieRepo: MovieRepository) : ViewModel() {

    fun getMoviesList(): MutableLiveData<List<MovieResponseItem>> {
        return movieRepo.getMovieList()
    }

     fun getMoviesListApi(page: Int) {
        movieRepo.getMoviesListApi(page)
    }

}