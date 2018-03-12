package com.zeyad.rxredux

import io.reactivex.annotations.NonNull

/**
 * @author ZIaDo on 2/27/18.
 */
interface ErrorMessageFactory {
    @NonNull
    fun getErrorMessage(throwable: Throwable): String
}