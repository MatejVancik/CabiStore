package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartItemData
import io.reactivex.Completable

interface UpdateCartItemUseCase {

    fun update(cartItemData: CartItemData): Completable

}