package com.cabify.store.cart.domain

import io.reactivex.Completable

interface DeleteCartItemUseCase {

    fun delete(productId: String): Completable

}