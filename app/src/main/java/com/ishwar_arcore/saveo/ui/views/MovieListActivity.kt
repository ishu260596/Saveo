package com.ishwar_arcore.saveo.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStoreOwner
import com.ishwar_arcore.saveo.R
import com.ishwar_arcore.saveo.databinding.ActivityMovieListBinding
import com.ishwar_arcore.saveo.repository.MovieRepository
import com.ishwar_arcore.saveo.ui.adapter.MovieAdapter
import com.ishwar_arcore.saveo.viewmodel.MovieViewModel
import com.ishwar_arcore.saveo.viewmodel.ViewModelFactory

class MovieListActivity : AppCompatActivity() {
    private var mBinding: ActivityMovieListBinding? = null

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieRepo: MovieRepository
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        initViews()
    }

    private fun initViews() {
        movieAdapter = MovieAdapter()
        movieRepo = MovieRepository()
        val viewModelFactory = ViewModelFactory(movieRepo)
        movieViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MovieViewModel::class.java)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}