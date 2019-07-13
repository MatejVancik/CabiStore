package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProvider
import io.reactivex.Completable

class DeleteCartItemUseCaseImpl(
    private val cartRepository: CartRepository,
    private val schedulerProvider: SchedulerProvider
): DeleteCartItemUseCase {

    override fun delete(cartItemData: CartItemData): Completable {
        return cartRepository.deleteCartItem(cartItemData)
            .observeOn(schedulerProvider.computation())
    }

}