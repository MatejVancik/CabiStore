package com.cabify.store.cart.domain.data

data class CartData(
    val items: List<CartItemData>,
    val fullPrice: Float,
    val discount: Float
) {
    val finalPrice: Float
        get() = fullPrice - discount
}

data class CartItemData(
    val code: String,
    val name: String = "",
    val pricePerItem: Float = 0F,
    val count: Int = 0,
    val discount: Discount? = null,
    val productImage: Int = 0
)