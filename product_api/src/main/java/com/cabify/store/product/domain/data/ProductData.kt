package com.cabify.store.product.domain.data

data class ProductData(
    val code: String,
    val name: String,
    val price: Float,
    val discountType: ProductDiscount? = null
)