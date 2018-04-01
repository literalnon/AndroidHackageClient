package com.example.bloold.hackage.view.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bloold.hackage.R
import com.example.bloold.hackage.base.adapters.AbstractAdapterDelegate
import com.example.bloold.hackage.base.adapters.AdapterDelegate
import kotlinx.android.synthetic.main.search_item.view.*

/**
 * Created by bloold on 01.04.18.
 */
class SearchDelegate: AbstractAdapterDelegate<Any, Any, SearchDelegate.Holder>() {
    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is ISearchModel
    }

    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, item: Any) {
        with(holder) {
            item as ISearchModel

            tvName.text = item.name
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tvName
    }
}