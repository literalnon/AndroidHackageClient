package com.example.bloold.hackage.view.search

import com.example.bloold.hackage.mvp.IPresenter
import com.example.bloold.hackage.network.RestApi
import com.example.bloold.hackage.network.services.SearchPackageService

/**
 * Created by bloold on 01.04.18.
 */
class SearchPresenter :
        IPresenter<SearchView,
        SearchModelManager>,
        ISearch,
        ISearchResponse {

    /** variable **/

    override var view: SearchView? = null
    override var model: SearchModelManager?  = null

    var service = RestApi.createService(SearchPackageService::class.java)

    /** ISearch methods **/

    override fun search(term: String) {

    }

    /** ISearchResponse methods **/

    override fun <Response : List<Any>> onSearchResult(response: Response) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /** IPresenter methods **/

    override fun attachView(view: SearchView) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun detachView(view: SearchView) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroyView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}