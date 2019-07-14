package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartData
import io.reactivex.Observable

interface ObserveCartUseCase {

    fun observe(): Observable<CartData>

}