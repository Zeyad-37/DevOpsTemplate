package com.privalia.test.mvi.viewmodel

/**
 * @author Zeyad Gasser.
 */
interface StateReducer<S> {
    fun reduce(newResult: Any, event: String, currentStateBundle: S): S
}