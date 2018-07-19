package com.privalia.test.screens.movies.list.events

import com.privalia.test.mvi.BaseEvent

data class GetMoviesEvent(private val page: Int) : BaseEvent<Int> {
    override fun getPayLoad(): Int {
        return page
    }
}
