package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartData
import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.ext.bindToCartItem
import com.cabify.store.product.domain.data.ProductData
import io.reactivex.functions.BiFunction

class CartProductZipper : BiFunction<List<CartItemData>, List<ProductData>, CartData> {

    override fun apply(cartItems: List<CartItemData>, products: List<ProductData>): CartData {
        val updatedCartItems = cartItems.mapNotNull { cartItem ->
            products.find { it.code == cartItem.code }
                ?.bindToCartItem(cartItem)
        }

        return buildCartData(updatedCartItems)
    }

    private fun buildCartData(items: List<CartItemData>): CartData {
        if (items.isEmpty()) return CartData(items, 0F, 0F)

        val fullPrice = items.map { it.pricePerItem * it.count }.reduce { acc, price -> acc + price }
        val discountedPrice = items.mapNotNull { it.discount?.invoke(it) }.reduce { acc, discount -> acc + discount }
        val discount = fullPrice - discountedPrice

        return CartData(items, fullPrice, discount)
    }

}