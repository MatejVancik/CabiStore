package com.cabify.store.cart

import com.cabify.store.cart.domain.data.CartData
import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.domain.data.Discount
import com.cabify.store.cart.presentation.data.CartItemDetailViewData
import com.cabify.store.cart.presentation.data.CartItemViewData
import com.cabify.store.cart.presentation.data.CartViewData

val mugCartItemData = CartItemData("MUG", "Mug", 7f, 2)
val mugCartItemDetailViewData = CartItemDetailViewData(
    name = "Mug",
    image = R.drawable.product_mug_small,
    count = 2,
    pricePerItem = "7.00€",
    discount = "0.00€",
    totalPrice = "14.00€"
)
val mugCartItemViewData = CartItemViewData(
    identifier = "MUG".hashCode().toLong(),
    code = "MUG",
    title = "2x Mug",
    finalPrice = "14.00€",
    originalPrice = null,
    image = R.drawable.product_mug_small
)

val voucherCartItemData = CartItemData("VOUCHER", "Voucher", 5f, 2, Discount.OnePlusOne)
val voucherCartItemViewData = CartItemViewData(
    identifier = "VOUCHER".hashCode().toLong(),
    code = "VOUCHER",
    title = "2x Voucher",
    finalPrice = "5.00€",
    originalPrice = "10.00€",
    image = R.drawable.product_vouche_small
)

val cartData = CartData(listOf(mugCartItemData, voucherCartItemData), 24f, 5f)
val cartViewData = CartViewData(
    items = listOf(mugCartItemViewData, voucherCartItemViewData),
    totalPrice = "24.00€",
    discount = "5.00€",
    finalPrice = "19.00€"
)