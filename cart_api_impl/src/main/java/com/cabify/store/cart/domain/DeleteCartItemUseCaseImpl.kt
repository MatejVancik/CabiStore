package com.cabify.store.cart.domain

import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProvider
import io.reactivex.Completable

class DeleteCartItemUseCaseImpl(
    private val cartRepository: CartRepository,
    private val schedulerProvider: SchedulerProvider
): DeleteCartItemUseCase {

    override fun delete(productId: String): Completable {
        return cartRepository.deleteCartItem(productId)
            .observeOn(schedulerProvider.computation())
    }

}