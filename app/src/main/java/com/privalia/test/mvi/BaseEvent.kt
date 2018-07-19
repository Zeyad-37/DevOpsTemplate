package com.privalia.test.mvi

/**
 * @author Zeyad Gasser.
 */
interface BaseEvent<T> {
    fun getPayLoad(): T
}