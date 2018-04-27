package com.example.bloold.hackage.view.search

import com.example.bloold.hackage.base.navigation.IBaseItem
import com.example.bloold.hackage.view.search.choice.ChoiceFragment

/**
 * Created by bloold on 12.04.18.
 */

enum class NavigationScreens : IBaseItem {
    CHOOSE_SEARCH_SCREEN {
        override var data: Any? = null

        override fun getTag() = "CHOOSE_SEARCH_SCREEN"

        override fun getPreviousEnumObject() = null

        override fun getFragment() = ChoiceFragment.newInstance()
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
    }


}
