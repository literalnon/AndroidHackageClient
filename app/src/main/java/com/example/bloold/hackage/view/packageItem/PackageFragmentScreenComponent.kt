package com.example.bloold.hackage.view.packageItem

import com.example.bloold.hackage.view.video.PackageFragmentModel
import com.example.bloold.hackage.view.video.PackageFragmentPresenter
import dagger.Component

/**
 * Created by dmitry on 07.05.18.
 */
@Component
interface PackageFragmentScreenComponent {
    fun getPresenter(): PackageFragmentPresenter
    fun getModel(): PackageFragmentModel
}