package com.example.bloold.hackage.view.video

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bloold.hackage.R
import com.example.bloold.hackage.base.adapters.DelegationAdapter
import com.example.bloold.hackage.view.model.VideoModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_video.*
import org.jsoup.Jsoup



class VideoFragment : Fragment() {

    companion object {
        fun newInstance(): VideoFragment {
            return VideoFragment()
        }
    }

    private val adapter = DelegationAdapter<Any>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.fragment_video, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        rvVideos.layoutManager = LinearLayoutManager(context)
        rvVideos.adapter = adapter

        adapter.manager.addDelegate(VideoDelegate())

        Observable.just(Jsoup.connect("https://www.haskell.org/"))
                .subscribeOn(Schedulers.io())
                .map {
                    it.get()
                            .body()
                            .getElementsByClass("videos")
                            .first()
                            .getElementsByClass(" row row-flex")
                            .first()
                            .getElementsByTag("a")
                            .map {
                                VideoModel(
                                    it.attributes().get("href"),
                                    it.attributes().get("title"),
                                    it.getElementsByTag("img").first().attributes().get("src")
                                )
                            }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.addAll(it)
                }, {

                })
    }
}
