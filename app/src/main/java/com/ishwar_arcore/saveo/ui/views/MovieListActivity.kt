package com.ishwar_arcore.saveo.ui.views


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private var count = 1

    private var mBinding: ActivityMovieListBinding? = null

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var moviePosterAdapter: MoviePosterAdapter
    private lateinit var movieListAdapter: MovieListAdapter
    private var movieList = mutableListOf<MovieResponseItem>()
    private var moviePosterList = mutableListOf<MovieResponseItem>()
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =
            ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)
        initViews()
        movieViewModel.getMoviesList().observe(this@MovieListActivity, Observer {
            shimmerDisplay()
            updateRecyclerView(it)
        })
    }

    /**
     * Shimmer display gone
     * **/
    private fun shimmerDisplay() {
        mBinding?.apply {
            shimmerFrameLayoutHorizontal.stopShimmerAnimation()
            shimmerFrameLayoutVertical.stopShimmerAnimation()
            shimmerFrameLayoutHorizontal.visibility = View.GONE
            shimmerFrameLayoutVertical.visibility = View.GONE
            rvMoviesList.visibility = View.VISIBLE
            rvMoviesPoster.visibility = View.VISIBLE
        }
    }

    private fun initViews() {
        mBinding?.shimmerFrameLayoutHorizontal?.startShimmerAnimation()
        mBinding?.shimmerFrameLayoutVertical?.startShimmerAnimation()

        moviePosterAdapter = MoviePosterAdapter(moviePosterList)
        movieListAdapter = MovieListAdapter(movieList, this)

        gridLayoutManager = GridLayoutManager(
            this@MovieListActivity, 3
        )
        val movieRepo = MovieRepository()

        val viewModelFactory = ViewModelFactory(movieRepo)
        movieViewModel = ViewModelProvider(this, viewModelFactory)
            .get(MovieViewModel::class.java)

        movieViewModel.getMoviesListApi(count)

        mBinding?.rvMoviesList?.apply {
            adapter = movieListAdapter
            layoutManager = gridLayoutManager
        }

        mBinding?.rvMoviesPoster?.apply {
            adapter = moviePosterAdapter
            layoutManager = LinearLayoutManager(
                this@MovieListActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        pagination()
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


    /**
     * Start pagination method called from the onCreate()
     * **/
    private fun pagination(
    ) {
        var loading = true
        var pastVisibleItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int
        mBinding?.rvMoviesList?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = gridLayoutManager.childCount
                    totalItemCount = gridLayoutManager.itemCount
                    pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                            count++
                            loading = false
                            movieViewModel.getMoviesPaginationListApi(count)
                            movieViewModel.getMoviesPagingList().observe(this@MovieListActivity, {
                                movieList.addAll(it)
                                movieListAdapter.notifyDataSetChanged()
                            })
                            loading = true
                        }
                    }
                }
            }
        })
    }
}