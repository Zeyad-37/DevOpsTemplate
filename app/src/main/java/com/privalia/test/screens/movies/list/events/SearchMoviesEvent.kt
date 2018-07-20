package com.privalia.test.screens.movies.list.events

import com.privalia.test.mvi.BaseEvent
import com.zeyad.gadapter.ItemInfo

data class SearchMoviesEvent(val query: String, val movies: List<ItemInfo>) : BaseEvent<Pair<String, List<ItemInfo>>> {
    override fun getPayLoad(): Pair<String, List<ItemInfo>> {
        return query to movies
    }
}
