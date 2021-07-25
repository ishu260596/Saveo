package com.ishwar_arcore.saveo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ishwar_arcore.saveo.data.model.MovieResponseItem
import com.ishwar_arcore.saveo.repository.MovieRepository

class MovieViewModel(private val movieRepo: MovieRepository) : ViewModel() {

    fun getMoviesList(): MutableLiveData<List<MovieResponseItem>> {
        return movieRepo.getMovieList()
    }

    fun getMoviesPagingList(): MutableLiveData<List<MovieResponseItem>> {
        return movieRepo.getMoviePagingList()
    }

    fun getMoviesListApi(page: Int) {
        movieRepo.getMoviesListApi(page)
    }

    fun getMoviesPaginationListApi(page: Int) {
        movieRepo.getMoviesPaginationListApi(page)
    }


}