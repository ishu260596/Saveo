package com.ishwar_arcore.saveo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ishwar_arcore.saveo.repository.MovieRepository

class ViewModelFactory(private val newsRepo: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(newsRepo) as T
    }
}