package com.example.bloold.hackage.view.search

import com.example.bloold.hackage.mvp.IPresenter
import com.example.bloold.hackage.network.RestApi
import com.example.bloold.hackage.network.services.SearchPackageService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

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
        service.searchPackages(term)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.onSearchResult(it)
                }, {
                    view?.onSearchResult(arrayListOf())
                })
    }

    /** ISearchResponse methods **/

    override fun <Response : List<Any>> onSearchResult(response: Response) {

    }

    /** IPresenter methods **/

    override fun attachView(view: SearchView) {
        this.view = view
    }

    override fun detachView(view: SearchView) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroyView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}