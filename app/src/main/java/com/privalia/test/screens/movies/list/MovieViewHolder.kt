package com.privalia.test.screens.movies.list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.privalia.test.PHOTOS_BASE_URL
import com.privalia.test.screens.movies.entities.Movie
import com.zeyad.gadapter.GenericRecyclerViewAdapter
import kotlinx.android.synthetic.main.movie_list_content.view.*

class MovieViewHolder(itemView: View) : GenericRecyclerViewAdapter.GenericViewHolder<Movie>(itemView) {

    override fun bindData(movie: Movie, isItemSelected: Boolean, position: Int, isEnabled: Boolean) {
        itemView.avatar?.let {
            it.viewTreeObserver?.addOnPreDrawListener {
                Glide.with(itemView.context)
                        .load("$PHOTOS_BASE_URL${it.measuredWidth}/${movie.posterPath}")
                        .into(it)
                true
            }
            itemView.title.text = movie.originalTitle
        }
    }

    fun getAvatar(): ImageView = itemView.avatar
    fun getTextViewTitle(): TextView = itemView.title
}