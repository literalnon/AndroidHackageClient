package com.example.bloold.hackage.view.search

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bloold.hackage.R
import android.content.Intent.getIntent
import android.webkit.WebView
import kotlinx.android.synthetic.main.fragment_hoogle.*
import android.webkit.WebViewClient




/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HoogleFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HoogleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HoogleFragment : Fragment() {

    companion object {

        fun newInstance(): HoogleFragment {
            return HoogleFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_hoogle, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webViewHoogleSearch.loadUrl(getString(R.string.hoogle_website))
        webViewHoogleSearch.webViewClient = MyWebViewClient()
    }
}

private class MyWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }
}