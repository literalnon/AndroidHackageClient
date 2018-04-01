package com.example.bloold.hackage.network

import android.content.Context
import android.util.Log
import com.example.bloold.hackage.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException


/**
 * Created by dmitry on 20.11.17.
 */
object RestApi {
    private val BASE_URL = NetworkConst.BASE_URL

    private var retrofit: Retrofit? = null

    fun init(authenticator: Authenticator) {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(BearerAuthorizationInterceptor())
                .authenticator(authenticator)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient.addNetworkInterceptor(interceptor)

        val gson = GsonBuilder().create()

        retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient.build())
                .build()
    }

    fun <T> createService(serviceClass: Class<T>): T {
        if (retrofit == null) {
            throw IllegalStateException("Call `RestApi.init(Context, Authenticator)` before calling this method.")
        }
        return retrofit!!.create(serviceClass)
    }
}

class BearerAuthorizationInterceptor : Interceptor {

    companion object {
        val TAG = "BearerAuthorization"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        try {
            val builder = chain.request().newBuilder()

            builder.addHeader(Const.Headers.ACCEPT, "application/json")

            try {
                return chain.proceed(builder.build())
            } catch (e: IOException) {

            }

            return chain.proceed(chain.request().newBuilder().build())

        } catch (e: SocketTimeoutException) {
            return chain.proceed(chain.request())
        }

    }
}