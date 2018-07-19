package com.privalia.test.mvi

import android.support.v4.util.Pair
import com.privalia.test.mvi.viewmodel.Result

/**
 * @author Zeyad Gasser.
 */
class UIModel<S>(private val stateName: String, throwable: Throwable?, eventBundlePair: Pair<String, S>,
                 isLoading: Boolean, isSuccessful: Boolean) :
        Result<S>(throwable, eventBundlePair, isLoading, isSuccessful) {

    override fun toString() = "State: $stateName, " +
            "event: ${eventBundlePair.first}, " +
            "Bundle: ${getBundle()}, " +
            "Error:" + " ${throwable?.message}, " +
            "Key Selector: ${when (stateName) {
                LOADING -> stateName
                else -> stateName + eventBundlePair.toString()
            }}"

    companion object {
        private const val LOADING = "loading"
        private const val ERROR = "throwable"
        private const val SUCCESS = "success"
        internal const val IDLE = "idle"

        fun <S> idleState(eventBundlePair: Pair<String, S>): UIModel<S> =
                UIModel(IDLE, null, eventBundlePair, false, false)

        internal fun <S> loadingState(eventBundlePair: Pair<String, S>): UIModel<S> =
                UIModel(LOADING, null, eventBundlePair, true, false)

        internal fun <S> errorState(error: Throwable?, eventBundlePair: Pair<String, S>): UIModel<S> =
                UIModel(ERROR, error, eventBundlePair, false, false)

        internal fun <S> successState(eventBundlePair: Pair<String, S>): UIModel<S> =
                UIModel(SUCCESS, null, eventBundlePair, false, true)
    }
}
