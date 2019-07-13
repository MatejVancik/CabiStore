package com.cabify.store.cart.presentation.data

import com.cabify.store.core.android.presentation.adapter.Identifiable

data class CartViewData(
    val items: List<CartItemViewData>,
    val totalPrice: String,
    val discount: String,
    val finalPrice: String
)

data class CartItemViewData(
    override val identifier: Long,
    val code: String,
    val title: String,
    val finalPrice: String,
    val discount: String?,
    val image: Int
): Identifiable
