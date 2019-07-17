package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartData
import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.domain.GetAllProductsUseCase
import com.cabify.store.product.domain.data.ProductData
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class ObserveCartUseCaseImpl(
    private val cartRepository: CartRepository,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val schedulerProvider: SchedulerProvider
) : ObserveCartUseCase {

    override fun observe(): Observable<CartData> {
        return Observable.combineLatest(
            cartRepository.observeCartItems(),
            getAllProductsUseCase.get().toObservable(),
            CartProductZipper()
        ).observeOn(schedulerProvider.computation())
    }

}