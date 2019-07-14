package com.cabify.store.cart.presentation.data

data class CartItemDetailViewData(
    val name: String,
    val image: Int,
    val count: Int,
    val pricePerItem: String,
    val discount: String?,
    val totalPrice: String
)