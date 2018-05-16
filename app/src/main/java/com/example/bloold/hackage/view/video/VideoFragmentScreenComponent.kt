package com.example.bloold.hackage.view.video

import dagger.Component

/**
 * Created by dmitry on 07.05.18.
 */
@Component
interface VideoFragmentScreenComponent {
    fun getPresenter(): VideoFragmentPresenter
}