package com.example.bloold.hackage.view.search.base

/**
 * Created by bloold on 01.04.18.
 */
interface ISearch {
    fun search(term: String)
}

interface ISearchResponse {
    fun <Response : List<Any>> onSearchResult(response: Response)
}