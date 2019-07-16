package com.cabify.store.cart

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.domain.data.Discount
import com.cabify.store.cart.repo.data.CartItemDto
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.domain.data.ProductDiscount

val mugCartItemDto = CartItemDto("MUG", 2)
val mugProductData = ProductData("MUG", "Mug", 7f)
val mugCartItemData = CartItemData("MUG", "Mug", 7f, 2)

val tshirtCartItemDto = CartItemDto("TSHIRT", 3)
val tshirtProductData = ProductData("TSHIRT", "T-Shirt", 20f, ProductDiscount.DiscountOnThree)
val tshirtCartItemData = CartItemData("TSHIRT", "T-Shirt", 20f, 3, Discount.DiscountOnThree)