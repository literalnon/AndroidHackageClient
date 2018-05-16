package com.example.bloold.hackage.view.model

/**
 * Created by bloold on 16.05.18.
 */
data class PackageModel(
        var readme: String? = null,
        var sourceRepo: String? = null,
        var author: String? = null,
        var description: String? = null,
        var license: String? = null,
        var maintainer: String? = null,
        var downloads: String? = null
)