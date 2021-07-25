package com.ishwar_arcore.saveo.ui.adapter

import android.widget.ImageView
import com.ishwar_arcore.saveo.data.model.MovieResponseItem

interface OnCardItemClickListener {
    fun onCardViewClick(model: MovieResponseItem, ivMovieImage: ImageView)
}