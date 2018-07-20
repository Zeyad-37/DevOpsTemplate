package com.privalia.test.screens.movies.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.privalia.test.R
import com.privalia.test.mvi.view.BaseView.UI_MODEL
import com.privalia.test.screens.movies.entities.Movie
import com.privalia.test.screens.movies.list.MovieListActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*

/**
 * An activity representing a single Movie detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [MovieListActivity].
 */
class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            val fragment = MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(UI_MODEL, intent.getStringExtra(UI_MODEL))
                }
            }
            supportFragmentManager.beginTransaction()
                    .add(R.id.movie_detail_container, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    navigateUpTo(Intent(this, MovieListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

    companion object {
        fun getCallingIntent(context: Context, movieDetailModel: Movie): Intent {
            return Intent(context, MovieDetailActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .putExtra(UI_MODEL, movieDetailModel)
        }
    }
}
