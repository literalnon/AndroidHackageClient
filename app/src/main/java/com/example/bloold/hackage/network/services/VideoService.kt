package com.example.bloold.hackage.network.services

import com.example.bloold.hackage.view.search.base.ISearchModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by bloold on 12.05.18.
 */
interface VideoService {

    @GET("video/{id}")
    fun getVideo(@Path("id") id: String): Observable<String>

}