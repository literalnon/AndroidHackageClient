package com.example.bloold.hackage.view.video

import com.example.bloold.hackage.mvp.IView
import com.example.bloold.hackage.view.model.PackageModel

/**
 * Created by dmitry on 04.05.18.
 */

interface IPackageFragmentView : IView<IPackageFragmentPresenter> {
    fun packageInfoIsLoaded(packageModel: PackageModel)
}
