package com.privalia.test.screens.movies.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.privalia.test.R
import com.privalia.test.mvi.view.BaseView.UI_MODEL
import com.privalia.test.screens.movies.entities.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a [MovieListActivity]
 * in two-pane mode (on tablets) or a [MovieDetailActivity]
 * on handsets.
 */
class MovieDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(UI_MODEL)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = DummyContent.ITEM_MAP[it.getString(ARG_MOVIE_ID)]
                activity?.toolbar_layout?.title = item?.content
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.movie_detail, container, false)

        // Show the dummy content as text in a TextView.
        item?.let {
            rootView.movie_detail.text = it.details
        }

        return rootView
    }

    companion object {

        fun newInstance(userDetailState: MovieDetailState): MovieDetailFragment {
            val userDetailFragment = MovieDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable(UI_MODEL, userDetailState)
            userDetailFragment.arguments = bundle
            return userDetailFragment
        }
    }
}
