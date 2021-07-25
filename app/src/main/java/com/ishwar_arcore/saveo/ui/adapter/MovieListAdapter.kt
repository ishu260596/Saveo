package com.ishwar_arcore.saveo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ishwar_arcore.saveo.R
import com.ishwar_arcore.saveo.data.model.MovieResponseItem

class MovieListAdapter(
    private val movieList: List<MovieResponseItem>,
    private val listener: OnCardItemClickListener
) :
    RecyclerView.Adapter<MovieListAdapter.MoviePosterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePosterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_layout, parent, false)
        return MoviePosterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviePosterViewHolder, position: Int) {
        val model = movieList[position]
        holder.setData(model)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MoviePosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivMovieImage = itemView.findViewById<ImageView>(R.id.ivImageMovie)
        private val cardView = itemView.findViewById<CardView>(R.id.cvMovieList)
        fun setData(model: MovieResponseItem) {
            val url = model.image.original
            Glide.with(itemView).load(url)
                .placeholder(R.drawable.placeholder).into(ivMovieImage)
            /**
             * Giving the call back to the activity
             * **/
            cardView.setOnClickListener {
                listener.onCardViewClick(model,ivMovieImage)
            }
        }
    }
}