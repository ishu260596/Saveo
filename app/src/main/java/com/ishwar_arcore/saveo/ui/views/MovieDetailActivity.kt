package com.ishwar_arcore.saveo.ui.views

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.cunoraz.tagview.Tag
import com.ishwar_arcore.saveo.R
import com.ishwar_arcore.saveo.data.model.MovieResponseItem
import com.ishwar_arcore.saveo.databinding.ActivityMovieDetailBinding
import com.ishwar_arcore.saveo.utils.KEY
import org.jsoup.Jsoup

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
                 * Set the summary to the text view
                 * and making the textview scrollable
                 * and removing the HTML tags
                 * **/
                tvSynopsis.movementMethod = ScrollingMovementMethod()
                val summary: String = Jsoup.parse(model?.summary).text()
                tvSynopsis.text = summary
                tvMovieName.text = model?.name.toString()

                /**
                 * Calculating the rating and assign to the stars
                 * **/
                starRatingBar.rating = model?.rating?.average!!.toFloat() / 2
                val rating = (model?.rating?.average!! / 2)
                val ratingString = String.format("%.1f", rating)
                tvStarRating.text = (ratingString)

                /**
                 * Set the date
                 * **/
                val date = formatDate(model?.premiered)
                val newDate = "R | 2h 17min | ${date}"
                tvTiming.text = newDate

                /**
                 * Set the tags
                 * **/
                val tagsList = model?.genres
                var tags = ArrayList<Tag>()
                if (tagsList != null) {
                    for (element in tagsList) {
                        val tag = Tag(element)
                        tag.radius = 10f
                        tags.add(tag)
                    }
                }
                tagContainerLayout.addTags(tags)
            }
        }
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