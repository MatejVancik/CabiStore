package com.cabify.store.cart.repo

import com.cabify.store.cart.domain.data.CartItemData
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CartRepository {

    fun observeCartItems(): Observable<List<CartItemData>>

    fun getCartItems(): Single<List<CartItemData>>

    fun addToCart(productId: String, count: Int): Completable

    fun getCartItem(productId: String): Single<CartItemData>

    fun updateCartItem(productId: String, count: Int): Completable

    fun deleteCartItem(productId: String): Completable
}