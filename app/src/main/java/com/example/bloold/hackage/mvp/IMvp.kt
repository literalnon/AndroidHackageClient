package com.example.bloold.hackage.mvp

import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import retrofit2.Call
import services.mobiledev.ru.cheap.navigation.INavigationParent
import services.mobiledev.ru.cheap.navigation.Navigator

/**
 * Created by bloold on 01.04.18.
 */
interface IView <Presenter> {
    var presenter: Presenter

    fun getNavigationParent(): INavigationParent

    fun showLoadingDialog(message: String? = null)

    fun dismissLoadingDialog()
}

interface IPresenter <View, Model> {
    var view: View?
    var model: Model
    val subscriptions: CompositeDisposable

    fun attachView(view: View)
    fun detachView(view: View)

    fun getNavigator(): Navigator?

    fun onDestroyView()

    val requestQueue: HashSet<Call<*>>

    fun cancelOnDestroy(call: Call<*>) {
        requestQueue.add(call)
    }

    fun removeRequest(call: Call<*>) {
        requestQueue.remove(call)
    }

    fun unsubscribeOnDestroy(disposable: Disposable) {
        subscriptions.add(disposable)
    }

    private fun clearRequestQueue() {
        subscriptions.clear()

        if (requestQueue.isEmpty()) {
            return
        }

        for (call in requestQueue) {
            call.cancel()
        }

        requestQueue.clear()
    }
}

interface IModel {
    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}