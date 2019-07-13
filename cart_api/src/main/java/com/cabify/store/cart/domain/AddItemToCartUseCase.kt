package com.cabify.store.cart.domain

import io.reactivex.Completable

interface AddItemToCartUseCase {

    fun add(productId: String, count: Int): Completable

}