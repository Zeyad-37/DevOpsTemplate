package com.privalia.test.mvi.view

/**
 * @author Zeyad Gasser.
 */
interface ErrorMessageFactory {
    fun getErrorMessage(throwable: Throwable): String
}