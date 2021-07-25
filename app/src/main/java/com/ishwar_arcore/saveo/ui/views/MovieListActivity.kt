package com.ishwar_arcore.saveo.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishwar_arcore.saveo.R
import com.ishwar_arcore.saveo.data.model.MovieResponseItem
import com.ishwar_arcore.saveo.databinding.ActivityMovieListBinding
import com.ishwar_arcore.saveo.repository.MovieRepository
import com.ishwar_arcore.saveo.ui.adapter.MovieListAdapter
import com.ishwar_arcore.saveo.ui.adapter.MoviePosterAdapter
import com.ishwar_arcore.saveo.viewmodel.MovieViewModel
import com.ishwar_arcore.saveo.viewmodel.ViewModelFactory

class MovieListActivity : AppCompatActivity() {
    private var mBinding: ActivityMovieListBinding? = null

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieRepo: MovieRepository
    private lateinit var moviePosterAdapter: MoviePosterAdapter
    private lateinit var movieListAdapter: MovieListAdapter
    private var moviePosters = mutableListOf<MovieResponseItem>()
    private var movieList = mutableListOf<MovieResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        initViews()
        movieViewModel.getMovies(1).observe(this, Observer {
            if (it != null)
                updateRecyclerView(it)
        })
    }

    private fun updateRecyclerView(it: List<MovieResponseItem>) {

    }

    private fun initViews() {
        moviePosterAdapter = MoviePosterAdapter(moviePosters)
        movieListAdapter = MovieListAdapter(movieList)

        movieRepo = MovieRepository()
        val viewModelFactory = ViewModelFactory(movieRepo)
        movieViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MovieViewModel::class.java)

        mBinding?.rvMoviesList?.apply {
            adapter = movieListAdapter
            layoutManager = GridLayoutManager(
                this@MovieListActivity, 3
            )
        }

        mBinding?.rvMoviesPoster?.apply {
            adapter = moviePosterAdapter
            layoutManager = LinearLayoutManager(
                this@MovieListActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}