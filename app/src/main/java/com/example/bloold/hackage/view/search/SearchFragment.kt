package com.example.bloold.hackage.view.search


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bloold.hackage.R
import com.example.bloold.hackage.base.adapters.DelegationAdapter
import com.example.bloold.hackage.view.search.adapter.SearchDelegate
import com.example.bloold.hackage.view.search.base.ISearch
import com.example.bloold.hackage.view.search.base.ISearchView
import kotlinx.android.synthetic.main.fragment_search_package.*
import services.mobiledev.ru.cheap.navigation.INavigationParent


/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment(), ISearchView, ISearch {

    companion object {
        const private val TAG = "FIND_FRAGMENT"
        const private val REQUEST_VOICE_SEARCH = 8972
        const private val EXTRA_WHAT_NAME = "what"

        enum class SearchWhat {
            USERS_SEARCH,
            PACKAGE_SEARCH
        }

        fun newInstance(what: SearchWhat): Fragment {
            return SearchFragment()
                    .apply { arguments = Bundle().apply { putString(EXTRA_WHAT_NAME, what.name) } }
        }

    }

    /** variable **/

    override var presenter: SearchPresenter = SearchPresenter()
    private val adapter: DelegationAdapter<Any> = DelegationAdapter()
    private var whatSearch: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        whatSearch = arguments.getString(EXTRA_WHAT_NAME, SearchWhat.PACKAGE_SEARCH.name)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_search_package, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivVoiceSearch.setOnClickListener {
            displaySpeechRecognizer()
        }

        ivCloseSearch.setOnClickListener {
            activity.onBackPressed()
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

        adapter.manager.addDelegate(SearchDelegate({ itemClick(it) }))

        presenter.attachView(this)
        search("a")
    }

    override fun onActivityResult(requestCode: Int, resCode: Int, data: Intent?) {
        if (requestCode == REQUEST_VOICE_SEARCH && resCode == Activity.RESULT_OK) {
            val result = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            etSearch.setText(result[0])
        }

        super.onActivityResult(requestCode, resCode, data)
    }

    /** View methods **/

    override fun <Response : List<Any>> onSearchResult(response: Response) {
        adapter.replaceAll(response)
    }

    override fun getNavigationParent(): INavigationParent {
        return activity as INavigationParent
    }

    override fun showLoadingDialog(message: String?) {

    }

    override fun dismissLoadingDialog() {

    }

    /** ISearch methods **/

    override fun search(term: String) {
        if (TextUtils.equals(SearchWhat.USERS_SEARCH.name, whatSearch)) {
            presenter?.searchUsers(term)
        } else {
            presenter?.search(term)
        }
    }

    override fun itemClick(id: String) {
        presenter.itemClick(id)
    }

    /** other private methods **/

    private fun displaySpeechRecognizer() {
        val intentS = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intentS.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        startActivityForResult(intentS, REQUEST_VOICE_SEARCH)
    }
}