package com.example.bloold.hackage

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import com.example.bloold.hackage.network.NetworkError
import com.example.bloold.hackage.network.RestApi
import com.jakewharton.threetenabp.AndroidThreeTen
import okhttp3.Authenticator

/**
 * Created by bloold on 01.04.18.
 */
class MainApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        RestApi.init(Authenticator( {route, response ->
                if (response.code() == 401) {
                    throw NetworkError(response.message())
                }

                return@Authenticator response.request()
            })
        )
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}