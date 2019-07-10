package com.cabify.store.product.presentation.view

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.cabify.store.core.android.presentation.adapter.BasicViewHolder
import com.cabify.store.core.android.presentation.adapter.ViewHolderManager
import com.cabify.store.core.android.presentation.extensions.inflateView
import com.cabify.store.product.R
import com.cabify.store.product.presentation.data.HomeProductItemViewData
import kotlinx.android.synthetic.main.item_home_product.*

class HomeProductViewHolderManager(
    private val onClickAction: (productId: String) -> Unit
) : ViewHolderManager<HomeProductItemViewData, BasicViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): BasicViewHolder {
        val view = parent.inflateView(R.layout.item_home_product)
        return BasicViewHolder(view)
    }

    override fun bindViewHolder(holder: BasicViewHolder, model: HomeProductItemViewData, position: Int) {
        holder.itemView.setOnClickListener { onClickAction(model.productId) }
        holder.title.text = model.title
        holder.price.text = model.price
        Glide.with(holder.containerView.context)
            .load(model.image)
            .into(holder.image)
    }

    override fun isSuitable(data: Any, position: Int) = data is HomeProductItemViewData

}