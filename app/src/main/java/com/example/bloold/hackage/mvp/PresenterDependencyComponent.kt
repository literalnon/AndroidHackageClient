package services.mobiledev.ru.cheap.mvp

import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call

/**
 * Created by dmitry on 08.05.18.
 */
@Component(modules = arrayOf(PresenterDependencyModule::class))
interface PresenterDependencyComponent {
    fun getSubscriptions(): CompositeDisposable
    fun getRequestQueue(): HashSet<Call<*>>
}