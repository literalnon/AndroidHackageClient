package com.example.bloold.hackage.mvp

/**
 * Created by bloold on 01.04.18.
 */
interface IView <Presenter> {
    var presenter: Presenter?

    fun attachPresenter(presenter: Presenter)

    fun showError(message: String?)

    fun showLoadingDialog(message: String? = null)

    fun dismissLoadingDialog()
}

interface IPresenter <View, Model> {
    var view: View?
    var model: Model?

    fun attachView(view: View)
    fun detachView(view: View)

    fun onDestroyView()
}

interface IModel <Presenter> {
    var presenter: Presenter


}