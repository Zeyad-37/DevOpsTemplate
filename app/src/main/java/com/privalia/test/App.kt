package com.privalia.test

import android.app.Application
import com.zeyad.usecases.api.DataServiceConfig
import com.zeyad.usecases.api.DataServiceFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * @author ZIaDo on 7/19/18.
 */
class App : Application() {

    val okHttpBuilder: OkHttpClient.Builder
        get() = OkHttpClient.Builder()
//                .addInterceptor(object : ProgressInterceptor(
//                        { bytesRead, contentLength, done -> RxEventBusFactory.getInstance(BackpressureStrategy.BUFFER).send(-1) }) {
//                    override fun isFileIO(originalResponse: Response): Boolean {
//                        return false
//                    }
//                }).addInterceptor(HttpLoggingInterceptor { message -> Log.d("NetworkInfo", message) }.setLevel(
//                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE))
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)


    override fun onCreate() {
        super.onCreate()
        DataServiceFactory.init(DataServiceConfig.Builder(this)
                .baseUrl(API_BASE_URL)
                .okHttpBuilder(okHttpBuilder)
                .build())
    }

    companion object {
        private val TIME_OUT = 15
    }
}
