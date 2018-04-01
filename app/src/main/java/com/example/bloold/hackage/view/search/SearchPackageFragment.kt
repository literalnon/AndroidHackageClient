package com.example.bloold.hackage.view.search


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.bloold.hackage.R
import com.example.bloold.hackage.base.adapters.DelegationAdapter
import com.example.bloold.hackage.mvp.IView
import kotlinx.android.synthetic.main.fragment_search_package.*


/**
 * A simple [Fragment] subclass.
 */
class SearchPackageFragment : Fragment(), SearchView, ISearch {

    companion object {

        private val TAG = "FIND_FRAGMENT"
        private val REQUEST_VOICE_SEARCH = 8972

        fun newInstance(): Fragment {
            return SearchPackageFragment()
        }

    }

    /** variable **/

    override var presenter: SearchPresenter? = null
    val adapter: DelegationAdapter<Any> = DelegationAdapter()

    /** Fragment methods **/

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_search_package, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivVoiceSearch.setOnClickListener {
            displaySpeechRecognizer()
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                search(s.toString())
            }
        })

        rvSearchResult.layoutManager = LinearLayoutManager(context)
        rvSearchResult.adapter = adapter

        adapter.manager.addDelegate(SearchDelegate())

        presenter = SearchPresenter()

        presenter?.attachView(this)
        presenter?.search("a")
    }

    override fun onActivityResult(requestCode: Int, resCode: Int, data: Intent?) {
        if (requestCode == REQUEST_VOICE_SEARCH && resCode == Activity.RESULT_OK) {
            val result = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            etSearch.setText(result[0])
        }

        super.onActivityResult(requestCode, resCode, data)
    }

    /** View methods **/

    override fun attachPresenter(presenter: SearchPresenter) {
        this.presenter = presenter
    }

    override fun <Response : List<Any>> onSearchResult(response: Response) {
        adapter.replaceAll(response)
    }

    override fun showError(message: String?) {

    }

    override fun showLoadingDialog(message: String?) {

    }

    override fun dismissLoadingDialog() {

    }

    /** ISearch methods **/

    override fun search(term: String) {
        presenter?.search(term)
    }

    /** other private methods **/

    private fun displaySpeechRecognizer() {
        val intentS = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intentS.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        startActivityForResult(intentS, REQUEST_VOICE_SEARCH)
    }
}

