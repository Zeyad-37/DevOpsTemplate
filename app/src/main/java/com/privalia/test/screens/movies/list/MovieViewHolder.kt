package com.privalia.test.screens.movies.list

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.privalia.test.screens.dummy.Movie
import com.zeyad.gadapter.GenericRecyclerViewAdapter

class MovieViewHolder(itemView: View) : GenericRecyclerViewAdapter.GenericViewHolder<Movie>(itemView) {

    override fun bindData(userModel: Movie, isItemSelected: Boolean, position: Int, isEnabled: Boolean) {
        if (userModel.avatarUrl!!.isNotEmpty()) {
            Glide.with(itemView.context).load(userModel.avatarUrl).into(itemView.avatar)
        } else {
            Glide.with(itemView.context)
                    .load(if ((Math.random() * 10).toInt() % 2 == 0)
                        "https://github.com/identicons/jasonlong.png"
                    else
                        "https://help.github.com/assets/images/help/profile/identicon.png")
                    .into(itemView.avatar)
        }
        if (userModel.login!!.isNotEmpty()) {
            itemView.title.text = userModel.login
        }
        itemView.setBackgroundColor(if (isItemSelected) Color.GRAY else Color.WHITE)
    }

    fun getAvatar(): ImageView = itemView.avatar
    fun getTextViewTitle(): TextView = itemView.title
}