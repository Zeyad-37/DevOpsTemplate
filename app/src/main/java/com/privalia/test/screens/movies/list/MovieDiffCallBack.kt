package com.privalia.test.screens.movies.list

import android.support.v7.util.DiffUtil
import com.privalia.test.screens.movies.entities.Movie

import com.zeyad.gadapter.ItemInfo

/**
 * @author ZIaDo on 12/13/17.
 */
class MovieDiffCallBack(private var newUsers: List<ItemInfo>, private var oldUsers: List<ItemInfo>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldUsers.size
    }

    override fun getNewListSize(): Int {
        return newUsers.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUsers[oldItemPosition].id == newUsers[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUsers[oldItemPosition].getData<Movie>() == newUsers[newItemPosition]
                .getData<Movie>()
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}
