package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartData
import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.domain.GetAllProductsUseCase
import io.reactivex.Observable

class ObserveCartUseCaseImpl(
    private val cartRepository: CartRepository,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val schedulerProvider: SchedulerProvider
): ObserveCartUseCase {

    override fun observe(): Observable<CartData> {
        return cartRepository.observeCartItems()
            .observeOn(schedulerProvider.computation())
            .map {
                val products = getAllProductsUseCase.get().blockingGet()
                CartProductZipper().apply(it, products)
            }
    }

}