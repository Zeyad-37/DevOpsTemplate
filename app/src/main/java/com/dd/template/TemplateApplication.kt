package com.dd.template

import android.app.Application
import android.os.StrictMode


class TemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build())
            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build())
        }

//        ReactiveNetwork.observeNetworkConnectivity(this)
//                .subscribeOn(Schedulers.io())
//                .filter(ConnectivityPredicate.hasState(NetworkInfo.State.CONNECTED))
//                .filter(ConnectivityPredicate.hasType(ConnectivityManager.TYPE_WIFI))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Consumer<Connectivity>() {
//                    fun accept(connectivity: Connectivity) {
//                        // do something
//                    }
//                })
    }
}
