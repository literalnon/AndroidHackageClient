package services.mobiledev.ru.cheap.mvp

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import java.util.HashSet

/**
 * Created by dmitry on 08.05.18.
 */
@Module
class PresenterDependencyModule {
    private val subscriptions: CompositeDisposable = CompositeDisposable()
    private val requestQueue: HashSet<Call<*>> = HashSet()

    @Provides
    fun getSubscriptions(): CompositeDisposable {
        return subscriptions
    }

    @Provides
    fun getRequestQueue(): HashSet<Call<*>> {
        return requestQueue
    }
}