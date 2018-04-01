package com.example.bloold.hackage.view.search

import com.example.bloold.hackage.mvp.IModel

/**
 * Created by bloold on 01.04.18.
 */
class SearchModelManager(override var presenter: SearchPresenter) : IModel<SearchPresenter>, ISearch, ISearchResponse {
    override fun search(term: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <Response : List<Any>> onSearchResult(response: Response) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}