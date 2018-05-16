package com.example.bloold.hackage.network.services

import com.example.bloold.hackage.view.search.base.ISearchModel
import com.example.bloold.hackage.view.search.base.IShortUserModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by bloold on 01.04.18.
 */
interface SearchPackageService {

    @GET("packages/search")
    fun searchPackages(@Query("terms") term: String): Observable<List<ISearchModel>>

    @GET("packages/search/")
    fun searchPackage(@Query("terms") term: String): Observable<List<ISearchModel>>

    @GET("users/")
    fun searchUsers(): Observable<List<IShortUserModel>>
}