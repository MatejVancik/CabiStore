package com.cabify.store.core.android.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.lang.IllegalStateException

class BasicAdapter<T>(
    vararg registeredManagers: ViewHolderManager<out T, out RecyclerView.ViewHolder>
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    var data: List<T> = arrayListOf()

    private val viewHolderManagers = HashMap<Int, ViewHolderManager<out T, out RecyclerView.ViewHolder>>()

    init {
        registeredManagers.forEach { this.viewHolderManagers[this.viewHolderManagers.size] = it }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = getViewHolderManagerForViewType(viewType)
        return holder.createViewHolder(parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val manager = getViewHolderManagerForViewType(viewHolder.itemViewType)
        manager.bindViewHolder(viewHolder, data[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        viewHolderManagers.forEach { (key, value) ->
            if (value.isSuitable(data[position] as Any, position)) return key
        }
        throw IllegalStateException("You need to assign delegate for position $position.")
    }

    override fun getItemId(position: Int): Long {
        return (data[position] as? Identifiable)?.identifier ?: super.getItemId(position)
    }

    override fun onViewRecycled(viewHolder: RecyclerView.ViewHolder) {
        val manager = getViewHolderManagerForViewType(viewHolder.itemViewType)
        manager.onViewRecycled(viewHolder)
    }

    private fun getViewHolderManagerForViewType(viewType: Int): ViewHolderManager<T, RecyclerView.ViewHolder> {
        return viewHolderManagers[viewType] as ViewHolderManager<T, RecyclerView.ViewHolder>?
            ?: throw IllegalStateException("You need to assign view holder for $viewType type.")
    }

}