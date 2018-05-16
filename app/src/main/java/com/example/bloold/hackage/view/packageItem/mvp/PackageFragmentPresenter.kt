package com.example.bloold.hackage.view.video

import android.util.Log
import com.example.bloold.hackage.mvp.IPresenter
import com.example.bloold.hackage.view.packageItem.DaggerPackageFragmentScreenComponent
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import services.mobiledev.ru.cheap.mvp.DaggerPresenterDependencyComponent
import services.mobiledev.ru.cheap.navigation.Navigator
import java.util.*
import javax.inject.Inject

/**
 * Created by dmitry on 04.05.18.
 */
interface IPackageFragmentPresenter : IPresenter<IPackageFragmentView, IPackageFragmentModel> {
    fun getPackage(id: String)
}

class PackageFragmentPresenter @Inject constructor() : IPackageFragmentPresenter {

    private val dependencyComponent = DaggerPresenterDependencyComponent.builder().build()
    private val screenComponent = DaggerPackageFragmentScreenComponent.builder().build()

    override val subscriptions: CompositeDisposable = dependencyComponent.getSubscriptions()
    override val requestQueue: HashSet<Call<*>> = dependencyComponent.getRequestQueue()

    override var view: IPackageFragmentView? = null

    override var model: IPackageFragmentModel = screenComponent.getModel()

    override fun getPackage(id: String) {
        model.getPackage(id)
                .subscribe({
                    view?.packageInfoIsLoaded(it)
                }, {
                    Log.d("tag", "error: $it")
                })
    }

    override fun attachView(view: IPackageFragmentView) {
        this.view = view
    }

    override fun detachView(view: IPackageFragmentView) {
        if (this.view?.equals(view) == true) {
            this.view = null
        }
    }

    override fun getNavigator(): Navigator? {
        return view?.getNavigationParent()?.navigator
    }

    override fun onDestroyView() {

    }

}
