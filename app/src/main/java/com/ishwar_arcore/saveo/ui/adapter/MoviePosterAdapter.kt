package com.ishwar_arcore.saveo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ishwar_arcore.saveo.R
import com.ishwar_arcore.saveo.data.model.MovieResponseItem

class MoviePosterAdapter(private val moviePosters: List<MovieResponseItem>) :
    RecyclerView.Adapter<MoviePosterAdapter.MoviePosterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_poster_layout,
                parent, false
            )
        return MoviePosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        val model = moviePosters[position]
        holder.setData(model)
    }

    override fun getItemCount(): Int {
        return moviePosters.size
    }

    inner class MoviePosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)

        fun setData(model: MovieResponseItem) {
            val url = model.image.original
            Glide.with(ivPoster).load(url).placeholder(R.drawable.pic).into(ivPoster)
        }

    }

}