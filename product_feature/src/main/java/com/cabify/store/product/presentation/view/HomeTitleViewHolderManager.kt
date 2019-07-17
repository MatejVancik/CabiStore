package com.cabify.store.product.presentation.view

import android.view.ViewGroup
import com.cabify.store.core.android.presentation.adapter.BasicViewHolder
import com.cabify.store.core.android.presentation.adapter.ViewHolderManager
import com.cabify.store.core.android.presentation.extensions.inflateView
import com.cabify.store.product.R
import com.cabify.store.product.presentation.data.HomeTitleItemViewData
import kotlinx.android.synthetic.main.item_home_title.*

class HomeTitleViewHolderManager: ViewHolderManager<HomeTitleItemViewData, BasicViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): BasicViewHolder {
        val view = parent.inflateView(R.layout.item_home_title)
        return BasicViewHolder(view)
    }

    override fun bindViewHolder(holder: BasicViewHolder, model: HomeTitleItemViewData, position: Int) {
        holder.itemView.contentDescription = model.title
        holder.text.text = model.title
    }

    override fun isSuitable(data: Any, position: Int) = data is HomeTitleItemViewData
}