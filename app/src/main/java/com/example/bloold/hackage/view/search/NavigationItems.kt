package com.example.bloold.hackage.view.search

import android.support.v4.app.Fragment
import com.example.bloold.hackage.view.packageItem.PackageFragment
import com.example.bloold.hackage.view.search.choice.ChoiceFragment
import com.example.bloold.hackage.view.video.VideoFragment
import services.mobiledev.ru.cheap.navigation.IBaseItem

/**
 * Created by bloold on 12.04.18.
 */

enum class NavigationScreens : IBaseItem {
    CHOOSE_SEARCH_SCREEN {
        override var data: Any? = null

        override fun getTag() = "CHOOSE_SEARCH_SCREEN"

        override fun getPreviousEnumObject() = null

        override fun getFragment(): Fragment = ChoiceFragment.newInstance()
    },
    HACKAGE_SEARCH_SCREEN {
        override var data: Any? = null

        override fun getTag() = "HACKAGE_SEARCH_SCREEN"

        override fun getPreviousEnumObject() = CHOOSE_SEARCH_SCREEN

        override fun getFragment() = SearchFragment.newInstance(SearchFragment.Companion.SearchWhat.PACKAGE_SEARCH)
    },
    HOOGLE_SEARCH_SCREEN {
        override var data: Any? = null

        override fun getTag() = "HOOGLE_SEARCH_SCREEN"

        override fun getPreviousEnumObject() = CHOOSE_SEARCH_SCREEN

        override fun getFragment() = HoogleFragment.newInstance()
    },
    USERS_SEARCH_SCREEN {
        override var data: Any? = null

        override fun getTag() = "USERS_SEARCH_SCREEN"

        override fun getPreviousEnumObject() = CHOOSE_SEARCH_SCREEN

        override fun getFragment() = SearchFragment.newInstance(SearchFragment.Companion.SearchWhat.USERS_SEARCH)
    },
    VIDEO_SCREEN {
        override var data: Any? = null

        override fun getTag() = "VIDEO_SCREEN"

        override fun getPreviousEnumObject() = CHOOSE_SEARCH_SCREEN

        override fun getFragment() = VideoFragment.newInstance()
    },
    PACKAGE_SCREEN {
        override var data: Any? = null

        override fun getTag() = "PACKAGE_SCREEN"

        override fun getPreviousEnumObject() = HACKAGE_SEARCH_SCREEN

        override fun getFragment() = PackageFragment.newInstance(data as String?)
    }


}
