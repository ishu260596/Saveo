package com.ishwar_arcore.saveo.ui.views

import android.os.Bundle
import android.text.SpannableString
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ishwar_arcore.saveo.data.model.MovieResponseItem
import com.ishwar_arcore.saveo.databinding.ActivityMovieDetailBinding
import com.ishwar_arcore.saveo.utils.KEY

class MovieDetailActivity : AppCompatActivity() {
    private var mBinding: ActivityMovieDetailBinding? = null
    private var model: MovieResponseItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =
            ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)
        initViews()
    }

    private fun initViews() {
        if (intent != null && intent.extras != null) {

            model = intent.getSerializableExtra(KEY) as MovieResponseItem
            mBinding?.apply {
                Glide.with(ivMoviePoster).load(model?.image?.original.toString())
                    .into(ivMoviePoster)

                /**
                 * Setting the summary to the text view
                 * and making the textview scrollable
                 * **/
                tvSynopsis.movementMethod = ScrollingMovementMethod()
                val summary = htmlToText(model?.summary.toString())
                tvSynopsis.text = summary.toString()
                tvMovieName.text = model?.name.toString()

                /**
                 * Calculating the rating and assign to the stars
                 * **/
                starRatingBar.rating = model?.rating?.average!!.toFloat() / 2
                val rating = (model?.rating?.average!! / 2)
                val ratingString = String.format("%.1f", rating)
                tvStarRating.text = (ratingString)

                /**
                 * Setting the date
                 * **/
                val date = formatDate(model?.premiered)
                val newDate = "R | 2h 17min | ${date}"
                tvTiming.text = newDate
            }
        }
    }

    private fun htmlToText(string: String): String? {
        return string.replace("\\<.*?\\>", "");
    }

    /**
     * Method for assigning the date as per month given
     * **/
    private fun formatDate(s: String?): String {
        val fullDate: List<String>? = s?.split("-")
        val month = when (fullDate?.get(1)) {
            "1" -> "Jan"
            "2" -> "Feb"
            "3" -> "March"
            "4" -> "April"
            "5" -> "May"
            "6" -> "June"
            "7" -> "July"
            "8" -> "Aug"
            "9" -> "Sep"
            "10" -> "Oct"
            "11" -> "Nov"
            else -> "Dec"
        }
        return (fullDate?.get(2) ?: "20") + " " + month + " " + (fullDate?.get(0) ?: "2002")
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}