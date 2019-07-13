package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartData
import io.reactivex.Single

interface GetCartUseCase {

    fun get(): Single<CartData>

}