package com.example.bloold.hackage.view.search

import com.example.bloold.hackage.mvp.IPresenter
import com.example.bloold.hackage.network.RestApi
import com.example.bloold.hackage.network.services.SearchPackageService
import com.example.bloold.hackage.view.search.base.ISearch
import com.example.bloold.hackage.view.search.base.ISearchResponse
import com.example.bloold.hackage.view.search.base.ISearchView
import com.example.bloold.hackage.view.search.model.SearchModelManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by bloold on 01.04.18.
 */
class SearchPresenter :
        IPresenter<ISearchView,
                SearchModelManager>,
        ISearch,
        ISearchResponse {

    /** variable **/

    override var view: ISearchView? = null
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

    override fun attachView(view: ISearchView) {
        this.view = view
    }

    override fun detachView(view: ISearchView) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroyView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}