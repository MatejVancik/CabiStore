package com.cabify.store.cart.presentation.view

import android.graphics.Paint
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.cabify.store.cart.R
import com.cabify.store.cart.presentation.data.CartItemViewData
import com.cabify.store.core.android.presentation.adapter.BasicViewHolder
import com.cabify.store.core.android.presentation.adapter.ViewHolderManager
import com.cabify.store.core.android.presentation.extensions.inflateView
import kotlinx.android.synthetic.main.item_cart.*
import kotlinx.android.synthetic.main.item_cart.view.*

class CartItemViewHolderManager(
    private val onItemClick: (CartItemViewData) -> Unit
) : ViewHolderManager<CartItemViewData, BasicViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): BasicViewHolder {
        val view = parent.inflateView(R.layout.item_cart)
        view.originalPrice.apply { paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG }
        return BasicViewHolder(view)
    }

    override fun bindViewHolder(holder: BasicViewHolder, model: CartItemViewData, position: Int) {
        holder.itemView.contentDescription = model.code
        holder.itemView.setOnClickListener { onItemClick(model) }

        holder.title.text = model.title
        holder.price.text = model.finalPrice
        holder.originalPrice.text = model.originalPrice

        Glide.with(holder.itemView.context).load(model.image).into(holder.image)
    }

    override fun isSuitable(data: Any, position: Int) = data is CartItemViewData

}