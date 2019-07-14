package com.cabify.store.cart.ext

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.domain.data.Discount
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.domain.data.ProductDiscount

internal fun ProductData.bindToCartItem(originalItem: CartItemData): CartItemData {
    return CartItemData(originalItem.code, name, price, originalItem.count, discountType?.toCartDiscount())
}

private fun ProductDiscount.toCartDiscount() = when (this) {
    ProductDiscount.OnePlusOne -> Discount.OnePlusOne
    ProductDiscount.DiscountOnThree -> Discount.DiscountOnThree
}