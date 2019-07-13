package com.cabify.store.cart.repo

import com.cabify.store.cart.domain.data.CartItemData
import io.reactivex.Completable
import io.reactivex.Single

interface CartRepository {

    fun getCartItems(): Single<List<CartItemData>>

    fun addToCart(productId: String, count: Int): Completable

    fun updateCartItem(cartItem: CartItemData): Completable

    fun deleteCartItem(cartItem: CartItemData): Completable
}