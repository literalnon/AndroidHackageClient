package com.example.bloold.hackage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.bloold.hackage.view.search.SearchPackageFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.container, SearchPackageFragment.newInstance())
                .commit()
    }
}
