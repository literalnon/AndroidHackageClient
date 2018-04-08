package com.example.bloold.hackage.base.navigation;

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by dmitry on 13.11.17.
 */
interface INavigationStrategy {
    val fragmentManager: FragmentManager
    val containerId: Int

    fun popFragment()
    fun showFragment(enumObject: IBaseItem, data: Any? = null)

    fun getCurrentScreen(): IBaseItem?
    fun clear()
    fun getCurrentFragment(): Fragment
    fun showFirstFragment(enumObject: IBaseItem, data: Any? = null)
    fun backStackSize(): Int

    fun showFragmentWithParcelable(enumObject: IBaseItem, fragment: Fragment, data: Any? = null)
    fun backNavigation(): Boolean
    fun updateUi(enumObject: IBaseItem?)

    fun updateChildUi(enumObject: IBaseItem?, data: Any? = null)
}

abstract class NavigatorTransactionManager(protected val fragmentManager: FragmentManager, protected val containerId: Int) {

    fun showFragment(enumObject: IBaseItem, data: Any? = null) {
        if(getCurrentScreen() == enumObject) {
            return
        }
    }
    abstract fun getCurrentScreen(): IBaseItem?
    abstract fun clear()
    abstract fun getCurrentFragment(): Fragment
    abstract fun showFirstFragment(enumObject: IBaseItem, data: Any? = null)
    open fun backStackSize(): Int {
        return fragmentManager.backStackEntryCount
    }
    abstract fun showFragmentWithParcelable(enumObject: IBaseItem, fragment: Fragment, data: Any? = null)
    abstract fun backNavigation(): Boolean
    abstract fun updateUi(enumObject: IBaseItem?)

    abstract fun updateChildUi(enumObject: IBaseItem?, data: Any? = null)
}

interface BaseNavigator {
    fun pushFragment(enumObject: IBaseItem, data: Any? = null)

    fun pushFragmentWithoutUpdate(enumObject: IBaseItem, data: Any? = null)

    fun openFirstFragment(data: Any? = null)

    fun showScreen(enumObject: IBaseItem, data: Any? = null)
}