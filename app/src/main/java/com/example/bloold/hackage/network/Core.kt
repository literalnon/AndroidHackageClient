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

    private const val RELEASE_API_URL = "http://174.138.57.251"
    private const val TEST_API_URL = "http://c519f64d.ngrok.io"

    const val HEADER_SECRET_KEY = "ServerKey"
    const val SECRET_KEY = "1a333186-bb8e-4364-98e5-c05f80f3bc1a"

    const val IMAGE_MAX_SIZE = 800
    const val IMAGE_COMPRESS_QUALITY = 80
    const val IMAGE_COMPRESSED_NAME = "compressedImage.jpg"

    fun getSocketUrl(accessToken: String, client: String, uid: String): String {
        return "$RELEASE_API_URL/cable?access-token=$accessToken&client=$client&uid=${URLEncoder.encode(uid, "utf-8")}"
    }
}

inline fun <reified T> genericType() = object : TypeToken<T>() {}.type!!

