package com.cabify.store.core.android.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolderManager<Model, VH : RecyclerView.ViewHolder>  {

    abstract fun createViewHolder(parent: ViewGroup): VH

    abstract fun bindViewHolder(holder: VH, model: Model, position: Int)

    abstract fun isSuitable(data: Any, position: Int): Boolean

    open fun onViewRecycled(holder: VH) {}

}