package com.cabify.store.product

import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.domain.data.ProductDiscount
import com.cabify.store.product.presentation.data.HomeProductItemViewData
import com.cabify.store.product.presentation.data.ProductDetailViewData

val mugProductData = ProductData("MUG", "Mug", 7f)
val mugProductDetailViewData = ProductDetailViewData("Mug", 0, null, "7.00€")
val mugHomeProductItemViewData = HomeProductItemViewData(
    identifier = "MUG".hashCode().toLong(),
    productId = "MUG",
    title = "Mug",
    price = "7.00€",
    image = R.drawable.product_mug_small
)

val voucherProductData = ProductData("VOUCHER", "Voucher", 5f, ProductDiscount.OnePlusOne)
val voucherProductDetailViewData = ProductDetailViewData("Voucher", R.drawable.product_vouche_small, "Promo text", "5.00€")
val voucherHomeProductItemViewData = HomeProductItemViewData(
    identifier = "VOUCHER".hashCode().toLong(),
    productId = "VOUCHER",
    title = "Voucher",
    price = "5.00€",
    image = R.drawable.product_vouche_small,
    hasDiscount = true
)