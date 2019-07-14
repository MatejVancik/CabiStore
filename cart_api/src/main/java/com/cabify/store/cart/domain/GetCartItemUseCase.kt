package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartItemData
import io.reactivex.Single

interface GetCartItemUseCase {

    fun get(productId: String): Single<CartItemData>

}