package services.mobiledev.ru.cheap.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * Created by dmitry on 20.11.17.
 */
abstract class ReplaceStrategy(override val fragmentManager: FragmentManager, override val containerId: Int) : INavigationStrategy {

    override fun popFragment() {
        fragmentManager.popBackStack()
    }

    override fun showFragment(enumObject: IBaseItem, data: Any?) {
        showFragmentWithParcelable(enumObject, enumObject.getFragment(), data)
    }

    override fun showFragmentWithParcelable(enumObject: IBaseItem, fragment: Fragment, data: Any?) {
        //super.showFragment(enumObject, data)

        enumObject.putAnimation(fragmentManager)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .add(containerId, fragment, enumObject.getTag())
                .commit()
    }

    override fun getCurrentScreen(): IBaseItem? {
        return null
    }

    override fun showFirstFragment(enumObject: IBaseItem, data: Any?) {
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .add(containerId, enumObject.getFragment(), enumObject.getTag())
                .commit()
    }

    override fun getCurrentFragment(): Fragment {
        return fragmentManager.findFragmentByTag(getCurrentScreen()?.getTag())
    }

    override fun clear() {
        for (i: Int in 0..backStackSize()) {
            fragmentManager.popBackStack()
        }
    }

    override fun backNavigation(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}