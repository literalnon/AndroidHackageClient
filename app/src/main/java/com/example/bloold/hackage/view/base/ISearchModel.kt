package com.example.bloold.hackage.view.search.base

import com.google.gson.annotations.SerializedName

/**
 * Created by bloold on 01.04.18.
 */
data class ISearchModel(
        var name: String? = null
)

data class IShortUserModel(
        @SerializedName("username")
        var name: String? = null,

        @SerializedName("userid")
        var id: String? = null
)