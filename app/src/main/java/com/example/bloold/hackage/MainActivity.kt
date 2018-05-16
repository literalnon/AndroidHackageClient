package com.example.bloold.hackage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.bloold.hackage.view.search.NavigationScreens
import services.mobiledev.ru.cheap.navigation.*

class MainActivity : AppCompatActivity(), INavigationParent {
    override var navigator: Navigator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navigator = Navigator.Builder()
                .firstFragment(NavigationScreens.CHOOSE_SEARCH_SCREEN)
                .strategy(AddBackStackStrategy(supportFragmentManager, R.id.container))
                .build()

        navigator?.openFirstFragment()
    }

    override fun onBackPressed() {
        if (navigator?.backNavigation() != true)
            super.onBackPressed()
    }
}
