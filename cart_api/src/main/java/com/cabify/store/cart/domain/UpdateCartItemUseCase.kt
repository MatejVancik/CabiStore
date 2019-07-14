package com.cabify.store.cart.domain

import io.reactivex.Completable

interface UpdateCartItemUseCase {

    fun update(productId: String, count: Int): Completable

}