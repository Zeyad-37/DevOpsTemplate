package com.privalia.test.screens.movies.list.events

import com.privalia.test.mvi.BaseEvent

data class SearchMoviesEvent(val query: String) : BaseEvent<String> {
    override fun getPayLoad(): String {
        return query
    }

}
