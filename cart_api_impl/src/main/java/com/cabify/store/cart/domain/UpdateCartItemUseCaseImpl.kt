package com.cabify.store.cart.domain

import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProvider
import io.reactivex.Completable

class UpdateCartItemUseCaseImpl(
    private val cartRepository: CartRepository,
    private val schedulerProvider: SchedulerProvider
): UpdateCartItemUseCase {

    override fun update(productId: String, count: Int): Completable {
        return cartRepository.updateCartItem(productId, count)
            .observeOn(schedulerProvider.computation())
    }

}