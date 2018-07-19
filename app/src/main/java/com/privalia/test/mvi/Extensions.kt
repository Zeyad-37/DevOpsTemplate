package com.privalia.test.mvi

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import org.reactivestreams.Publisher

/**
 * @author ZIaDo on 6/13/18.
 */
fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>