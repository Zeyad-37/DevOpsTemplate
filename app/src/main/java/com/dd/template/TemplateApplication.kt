package com.dd.template

import android.app.Application
import android.os.StrictMode
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher


class TemplateApplication : Application() {
    private lateinit var refWatcher: RefWatcher
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

        refWatcher = LeakCanary.install(this)

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

    fun getRefWatcher(): RefWatcher {
        return refWatcher
    }
}
