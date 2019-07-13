package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartItemData
import io.reactivex.Completable

interface DeleteCartItemUseCase {

    fun delete(cartItemData: CartItemData): Completable

}