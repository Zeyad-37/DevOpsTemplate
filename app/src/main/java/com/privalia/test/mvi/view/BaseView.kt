package com.privalia.test.mvi.view

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable

/**
 * @author Zeyad Gasser.
 */
object BaseView {
    const val UI_MODEL = "viewState"
    private const val VIEW_STATE_ERROR_MSG = "viewState"

    fun <S : Parcelable> getViewStateFrom(savedInstanceState: Bundle?, intent: Intent?): S =
            if (savedInstanceState != null && savedInstanceState.containsKey(UI_MODEL)) {
                savedInstanceState.getParcelable(UI_MODEL)
            } else if (intent != null && intent.hasCategory(UI_MODEL)) {
                intent.getParcelableExtra(UI_MODEL)
            } else throw IllegalAccessException(VIEW_STATE_ERROR_MSG)

    fun <S : Parcelable> getViewStateFrom(savedInstanceState: Bundle?, arguments: Bundle?): S =
            if (savedInstanceState != null && savedInstanceState.containsKey(UI_MODEL)) {
                savedInstanceState.getParcelable(UI_MODEL)
            } else if (arguments != null && arguments.containsKey(UI_MODEL)) {
                arguments.getParcelable(UI_MODEL)
            } else throw IllegalAccessException(VIEW_STATE_ERROR_MSG)
}

