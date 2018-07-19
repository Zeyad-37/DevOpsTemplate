package com.privalia.test.mvi.viewmodel

import io.reactivex.annotations.NonNull

/**
 * @author Zeyad Gasser.
 */
interface StateReducer<S> {
    fun reduce(@NonNull newResult: Any?, @NonNull event: String?, @NonNull currentStateBundle: S?): S
}