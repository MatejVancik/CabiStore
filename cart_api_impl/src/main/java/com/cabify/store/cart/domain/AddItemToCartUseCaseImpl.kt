package com.cabify.store.cart.domain

import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProvider
import io.reactivex.Completable

class AddItemToCartUseCaseImpl(
    private val cartRepository: CartRepository,
    private val schedulerProvider: SchedulerProvider
): AddItemToCartUseCase {

    override fun add(productId: String, count: Int): Completable {
        return cartRepository.addToCart(productId, count)
            .observeOn(schedulerProvider.computation())
    }

}