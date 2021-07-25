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
    private lateinit var moviePosterAdapter: MoviePosterAdapter
    private lateinit var movieListAdapter: MovieListAdapter
    private var movieList = mutableListOf<MovieResponseItem>()
    private var moviePosterList = mutableListOf<MovieResponseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =
            ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)
        initViews()

        movieViewModel.getMoviesList().observe(this, Observer {
            updateRecyclerView(it)
        })
    }

    private fun initViews() {
        moviePosterAdapter = MoviePosterAdapter(moviePosterList)
        movieListAdapter = MovieListAdapter(movieList)

        val movieRepo = MovieRepository()
        val viewModelFactory = ViewModelFactory(movieRepo)
        movieViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MovieViewModel::class.java)

        movieViewModel.getMoviesListApi(1)

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

    private fun updateRecyclerView(it: List<MovieResponseItem>) {
        movieList.addAll(it)
        for (i in 0..8) {
            moviePosterList.add(movieList[i])
        }
        movieListAdapter.notifyDataSetChanged()
        moviePosterAdapter.notifyDataSetChanged()
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}