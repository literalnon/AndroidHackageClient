package com.example.bloold.hackage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bloold.hackage.base.navigation.AddBackStackStrategy
import com.example.bloold.hackage.base.navigation.IBaseItem
import com.example.bloold.hackage.base.navigation.Navigator
import com.example.bloold.hackage.base.navigation.SimpleParent
import com.example.bloold.hackage.view.search.NavigationScreens
import com.example.bloold.hackage.view.search.SearchPackageFragment

class MainActivity : AppCompatActivity(), SimpleParent {

    private var navigator: Navigator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        /*supportFragmentManager.beginTransaction()
                .add(R.id.container, SearchPackageFragment.newInstance())
                .commit()*/

        navigator = Navigator.NavigatorBuilder()
                .setFirstFragment(NavigationScreens.CHOOSE_SEARCH_SCREEN)
                .setStrategy(AddBackStackStrategy(supportFragmentManager, R.id.container))
                .Build()

        navigator?.openFirstFragment()
    }

    override fun action(item: IBaseItem?, data: Any?) {
        if (item != null && item != navigator?.getCurrentScreen()) {
            navigator?.showScreen(item, data)
        }
    }

    override fun onBackPressed() {
        if (navigator?.backNavigation() != true)
            super.onBackPressed()
    }
}
