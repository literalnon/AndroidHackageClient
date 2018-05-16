package com.example.bloold.hackage.view.search.model

import com.example.bloold.hackage.mvp.IModel
import com.example.bloold.hackage.view.search.SearchPresenter
import com.example.bloold.hackage.view.search.base.ISearch
import com.example.bloold.hackage.view.search.base.ISearchResponse

/**
 * Created by bloold on 01.04.18.
 */
class SearchModelManager: IModel, ISearch, ISearchResponse {
    override fun itemClick(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun search(term: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <Response : List<Any>> onSearchResult(response: Response) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}