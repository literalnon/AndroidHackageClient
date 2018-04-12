package com.example.bloold.hackage.view.search.choice


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bloold.hackage.R
import com.example.bloold.hackage.base.navigation.BaseChildFragment
import com.example.bloold.hackage.base.navigation.IBaseItem
import com.example.bloold.hackage.view.search.NavigationScreens
import kotlinx.android.synthetic.main.fragment_choice.*


/**
 * A simple [Fragment] subclass.
 */
class ChoiceFragment : BaseChildFragment() {

    companion object {
        fun newInstance(): ChoiceFragment {
            return ChoiceFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_choice, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvHoogleChoice.setOnClickListener(View.OnClickListener {
            parent?.action(NavigationScreens.HOOGLE_SEARCH_SCREEN)
        })

        tvHackageChoice.setOnClickListener(View.OnClickListener {
            parent?.action(NavigationScreens.HACKAGE_SEARCH_SCREEN)
        })
    }

    override fun callback(item: IBaseItem, data: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
