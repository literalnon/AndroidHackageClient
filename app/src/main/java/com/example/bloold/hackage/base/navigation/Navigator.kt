package com.example.bloold.hackage.base.navigation

import android.os.Build
import android.support.v4.app.FragmentManager
import android.util.Log

/**
 * Created by bloold on 08.04.18.
 */
class Navigator: BaseNavigator {

    class NavigatorBuilder {

        private var navigator: Navigator = Navigator()

        fun Build(): Navigator {
            return navigator
        }

        fun setStrategy(navigationStrategy: INavigationStrategy): NavigatorBuilder {
            navigator.navigationStrategy = navigationStrategy
            return this
        }

        fun setFirstFragment(firstFragment: IBaseItem): NavigatorBuilder {
            navigator.firstFragment = firstFragment
            return this
        }
    }


    private var navigationStrategy: INavigationStrategy? = null
    private var firstFragment: IBaseItem? = null

    override fun showScreen(enumObject: IBaseItem, data: Any?) {
        pushFragment(enumObject, data)
    }

    override fun pushFragment(enumObject: IBaseItem, data: Any?) {
        navigationStrategy?.showFragment(enumObject, data)
        navigationStrategy?.updateUi(enumObject)
    }

    override fun pushFragmentWithoutUpdate(enumObject: IBaseItem, data: Any?) {
        navigationStrategy?.showFragment(enumObject, data)
    }

    override fun openFirstFragment(data: Any?) {
        if (firstFragment == null) {
            throw RuntimeException("must include first fragment")
        } else {
            navigationStrategy?.showFirstFragment(firstFragment!!, data)
        }
    }

    override fun getCurrentScreen(): IBaseItem? {
        return navigationStrategy?.getCurrentScreen()
    }

    override fun backNavigation(): Boolean {
        return navigationStrategy?.backNavigation() ?: false
    }

}