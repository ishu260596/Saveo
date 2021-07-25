package com.ishwar_arcore.saveo.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishwar_arcore.saveo.data.model.MovieResponseItem
import com.ishwar_arcore.saveo.databinding.ActivityMovieListBinding
import com.ishwar_arcore.saveo.repository.MovieRepository
import com.ishwar_arcore.saveo.ui.adapter.MovieListAdapter
import com.ishwar_arcore.saveo.ui.adapter.MoviePosterAdapter
import com.ishwar_arcore.saveo.ui.adapter.OnCardItemClickListener
import com.ishwar_arcore.saveo.utils.KEY
import com.ishwar_arcore.saveo.viewmodel.MovieViewModel
import com.ishwar_arcore.saveo.viewmodel.ViewModelFactory

class MovieListActivity : AppCompatActivity(), OnCardItemClickListener {
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
        movieListAdapter = MovieListAdapter(movieList, this)

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
        for (i in 0..6) {
            moviePosterList.add(movieList[i])
        }
        movieListAdapter.notifyDataSetChanged()
        moviePosterAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    override fun onCardViewClick(model: MovieResponseItem, ivMovieImage: ImageView) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(KEY, model)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            ivMovieImage,
            ViewCompat.getTransitionName(ivMovieImage)!!
        )
        startActivity(intent, options.toBundle())
    }
}