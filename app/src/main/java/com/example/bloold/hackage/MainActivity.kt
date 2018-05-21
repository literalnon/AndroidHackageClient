package com.example.bloold.hackage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.bloold.hackage.view.search.NavigationScreens
import kotlinx.android.synthetic.main.fragment_choice.*
import services.mobiledev.ru.cheap.navigation.*

class MainActivity : AppCompatActivity(), INavigationParent {
    override var navigator: Navigator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navigator = Navigator.Builder()
                .firstFragment(NavigationScreens.HACKAGE_SEARCH_SCREEN)
                .strategy(AddBackStackStrategy(supportFragmentManager, R.id.container))
                .build()

        navigator?.openFirstFragment()

        tvHoogleChoice.setOnClickListener({
            navigator?.showScreen(NavigationScreens.HOOGLE_SEARCH_SCREEN)
        })

        tvHackageChoice.setOnClickListener({
            navigator?.showScreen(NavigationScreens.HACKAGE_SEARCH_SCREEN)
        })

        tvUsersChoice.setOnClickListener({
            navigator?.showScreen(NavigationScreens.USERS_SEARCH_SCREEN)
        })

        tvVideoChoice.setOnClickListener({
            navigator?.showScreen(NavigationScreens.VIDEO_SCREEN)
        })
    }

    override fun onBackPressed() {
        if (navigator?.backNavigation() != true)
            super.onBackPressed()
    }
}
