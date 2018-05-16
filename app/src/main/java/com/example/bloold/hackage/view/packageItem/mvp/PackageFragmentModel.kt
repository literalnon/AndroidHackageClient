package com.example.bloold.hackage.view.video

import android.text.TextUtils
import com.example.bloold.hackage.mvp.IModel
import com.example.bloold.hackage.view.model.PackageModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

/**
 * Created by dmitry on 04.05.18.
 */
interface IPackageFragmentModel : IModel {
    fun getPackage(id: String): Observable<PackageModel>
}

class PackageFragmentModel @Inject constructor() : IPackageFragmentModel {

    override fun getPackage(id: String): Observable<PackageModel> {
        return Observable.just(Jsoup.connect("http://hackage.haskell.org/package/$id"))
                .subscribeOn(Schedulers.io())
                .map {
                    val body = it.get().body()
                    val properties = body.getElementsByClass("properties").first().getElementsByTag("tr")

                    PackageModel(body.getElementsByClass("embedded-author-content").first()?.text(),
                            getElementWithTitle(properties, "Source repo")?.getElementsByClass("word-wrap")?.first()?.text(),
                            getElementWithTitle(properties, "Author")?.getElementsByClass("word-wrap")?.first()?.text(),
                            body.getElementById("description")?.text(),
                            getElementWithTitle(properties, "License")?.getElementsByClass("word-wrap")?.first()?.text(),
                            getElementWithTitle(properties, "Maintainer")?.getElementsByClass("word-wrap")?.first()?.text(),
                            getElementWithTitle(properties, "Downloads")?.text()
                    )

                }
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getElementWithTitle(elements: Elements, title: String): Element? {
        return elements.find { TextUtils.equals(it.getElementsByTag("th").first().text(), title) }
    }
}


