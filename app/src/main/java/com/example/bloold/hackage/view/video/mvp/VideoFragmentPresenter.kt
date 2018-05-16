package com.example.bloold.hackage.view.video

import com.example.bloold.hackage.mvp.IPresenter
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import services.mobiledev.ru.cheap.mvp.DaggerPresenterDependencyComponent
import services.mobiledev.ru.cheap.navigation.Navigator
import java.util.*
import javax.inject.Inject

/**
 * Created by dmitry on 04.05.18.
 */
interface IVideoFragmentPresenter : IPresenter<IVideoFragmentView, VideoFragmentModel> {

}

class VideoFragmentPresenter @Inject constructor() : IVideoFragmentPresenter {
    private val dependencyComponent = DaggerPresenterDependencyComponent.builder().build()

    override val subscriptions: CompositeDisposable = dependencyComponent.getSubscriptions()
    override val requestQueue: HashSet<Call<*>> = dependencyComponent.getRequestQueue()

    override var view: IVideoFragmentView? = null

    override var model: VideoFragmentModel = VideoFragmentModel()

    override fun attachView(view: IVideoFragmentView) {
        this.view = view
    }

    override fun detachView(view: IVideoFragmentView) {
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
