package com.privalia.test.screens.movies.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.privalia.test.PHOTOS_BASE_URL
import com.privalia.test.R
import com.privalia.test.getYear
import com.privalia.test.mvi.view.BaseView.UI_MODEL
import com.privalia.test.screens.movies.entities.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.movie_detail.view.*

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a [MovieListActivity]
 * in two-pane mode (on tablets) or a [MovieDetailActivity]
 * on handsets.
 */
class MovieDetailFragment : Fragment() {

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(UI_MODEL)) {
                movie = it.getParcelable(UI_MODEL)
                activity?.let {
                    it.toolbar_layout?.title = movie.title
                    it.iv_movie_detail?.let {
                        it.viewTreeObserver?.addOnPreDrawListener {
                            Glide.with(this@MovieDetailFragment)
                                    .load("$PHOTOS_BASE_URL${it.measuredWidth}/${movie.posterPath}")
                                    .into(it)
                            true
                        }
                    }
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.movie_detail, container, false)
        rootView.tv_overview.text = movie.overview
        rootView.tv_year.text = movie.releaseDate.getYear()
        return rootView
    }

    companion object {

        fun newInstance(movie: Movie): MovieDetailFragment {
            val movieDetailFragment = MovieDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(UI_MODEL, movie)
            movieDetailFragment.arguments = bundle
            return movieDetailFragment
        }
    }
}


