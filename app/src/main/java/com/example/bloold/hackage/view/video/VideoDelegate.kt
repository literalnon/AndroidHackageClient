package com.example.bloold.hackage.view.video

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.bloold.hackage.GlideApp
import com.example.bloold.hackage.R
import com.example.bloold.hackage.base.adapters.AbstractAdapterDelegate
import com.example.bloold.hackage.view.model.VideoModel
import com.example.bloold.hackage.view.video.video.VideosActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.video_item.view.*
import org.jsoup.Jsoup

/**
 * Created by bloold on 09.05.18.
 */
class VideoDelegate: AbstractAdapterDelegate<Any, Any, VideoDelegate.Holder>() {
    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is VideoModel
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, item: Any) {
        with(holder) {

            item as VideoModel

            tvName.text = item.title

            GlideApp.with(tvName.context)
                    .load(item.image)
                    .into(ivPreview)

            val context = itemView.context

            val id = item.ref?.split("/")?.last()

            itemView.setOnClickListener{
                Observable.just(Jsoup.connect("https://player.vimeo.com/video/$id/"))
                        .subscribeOn(Schedulers.io())
                        .map {

                            val list = it.get()
                                    .body().toString()
                                    .split("progressive")
                                    .last()
                                    .split("\"")

                            list[list.indexOf("url") + 2]
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            context.startActivity(VideosActivity.getIntent(context, it))
                        }, {
                            Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
                        })
            }
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tvName
        val ivPreview = itemView.ivPreview
    }
}

