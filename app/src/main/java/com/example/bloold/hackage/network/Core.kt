package com.example.bloold.hackage.network

import com.google.gson.reflect.TypeToken
import java.net.URLEncoder

/**
 * Created by dmitry on 20.11.17.
 */
object NetworkConst {
    const val TAG = "NetworkConst"

    val BASE_URL: String
        get() = RELEASE_API_URL

    private const val RELEASE_API_URL = "http://hackage.haskell.org/"

    fun getSocketUrl(accessToken: String, client: String, uid: String): String {
        return "$RELEASE_API_URL/cable?access-token=$accessToken&client=$client&uid=${URLEncoder.encode(uid, "utf-8")}"
    }
}

inline fun <reified T> genericType() = object : TypeToken<T>() {}.type!!

