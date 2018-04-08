package com.example.bloold.hackage.base.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import java.util.*

/**
 * Created by dmitry on 20.11.17.
 */
abstract class AddBackStackStrategy(fragmentManager: FragmentManager, containerId: Int) :
        NavigatorTransactionManager(fragmentManager, containerId) {

    private val tags: Stack<IBaseItem?> = Stack()

    override fun popFragment() {
        tags.pop()
        fragmentManager.popBackStack()
    }

    override fun showFragment(enumObject: IBaseItem, data: Any?) {
        showFragmentWithParcelable(enumObject, enumObject.getFragment(), data)
    }

    override fun getCurrentScreen(): IBaseItem? {
        return if (tags.empty()) {
                    null
                } else {
                    tags.peek()
                }
    }

    override fun showFirstFragment(enumObject: IBaseItem, data: Any?) {
        showFragment(enumObject, data)
    }

    override fun showFragmentWithParcelable(enumObject: IBaseItem, fragment: Fragment, data: Any?) {
        super.showFragment(enumObject, data)

        if (tags.contains(enumObject)) {
            while(!tags.empty() && tags.peek() != enumObject) {
                tags.pop()
                fragmentManager.popBackStack()
            }
        } else {
            tags.push(enumObject)

            enumObject.putAnimation(fragmentManager)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .add(containerId, fragment, enumObject.getTag())
                    .addToBackStack(enumObject.getTag())
                    .commit()
        }
    }

    override fun getCurrentFragment(): Fragment {
        return fragmentManager.findFragmentByTag(getCurrentScreen()?.getTag())
    }

    override fun clear() {
        tags.clear()

        for (i: Int in 0..backStackSize()) {
            fragmentManager.popBackStack()
        }
    }

    override fun backNavigation(): Boolean {
        when {
            tags.empty() -> {
                //Log.d("backNavigation", "1")
                return false
            }
            tags.size == 1 -> {
                //Log.d("backNavigation", "2")
                popFragment()
                return false
            }
            else -> {
                Log.d("backNavigation", "tags.size > 1")

                val currentEnumObject: IBaseItem? = tags.peek()

                if (currentEnumObject?.getPreviousEnumObject() == null) {
                    clear()
                }

                Log.d("backNavigation", "see ${tags.peek()?.getTag()} cur: ${currentEnumObject?.getPreviousEnumObject()?.getTag()}")

                while (!tags.empty() && tags.peek()?.getTag() != currentEnumObject?.getPreviousEnumObject()?.getTag()) {
                    Log.d("backNavigation", "pop ${tags.peek()?.getTag()} cur: ${currentEnumObject?.getPreviousEnumObject()?.getTag()}")
                    popFragment()
                }

                if(!tags.empty())
                    updateUi(tags.peek())

                return true
            }
        }
    }
}