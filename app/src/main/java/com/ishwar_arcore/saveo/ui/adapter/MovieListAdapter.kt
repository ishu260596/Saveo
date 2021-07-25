package com.ishwar_arcore.saveo.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ishwar_arcore.saveo.data.model.MovieResponseItem

class MovieListAdapter(movieList: MutableList<MovieResponseItem>) : RecyclerView.Adapter<MovieListAdapter.MoviePosterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class MoviePosterViewHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView) {
    }
}