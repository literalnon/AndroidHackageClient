package com.example.bloold.hackage.network.services

import retrofit2.http.Field
import retrofit2.http.GET

/**
 * Created by bloold on 01.04.18.
 */
interface SearchPackageService {

    @GET("packages/search")
    fun searchPackages(@Field("terms") term: String)

}