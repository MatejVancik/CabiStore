package com.cabify.store.cart.presentation.view

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.cabify.store.cart.R
import com.cabify.store.cart.presentation.data.CartItemViewData
import com.cabify.store.core.android.presentation.adapter.BasicViewHolder
import com.cabify.store.core.android.presentation.adapter.ViewHolderManager
import com.cabify.store.core.android.presentation.extensions.inflateView
import kotlinx.android.synthetic.main.item_cart.*

class CartItemViewHolderManager(
    private val onItemClick: (CartItemViewData) -> Unit
) : ViewHolderManager<CartItemViewData, BasicViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): BasicViewHolder {
        val view = parent.inflateView(R.layout.item_cart)
        return BasicViewHolder(view)
    }

    override fun bindViewHolder(holder: BasicViewHolder, model: CartItemViewData, position: Int) {
        holder.itemView.setOnClickListener { onItemClick(model) }

        holder.title.text = model.title
        holder.price.text = model.finalPrice
        holder.discount.text = model.discount

        Glide.with(holder.itemView.context).load(model.image).into(holder.image)
    }

    override fun isSuitable(data: Any, position: Int) = data is CartItemViewData

}