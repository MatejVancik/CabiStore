package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProvider
import io.reactivex.Completable

class UpdateCartItemUseCaseImpl(
    private val cartRepository: CartRepository,
    private val schedulerProvider: SchedulerProvider
): UpdateCartItemUseCase {

    override fun update(cartItemData: CartItemData): Completable {
        return cartRepository.updateCartItem(cartItemData)
            .observeOn(schedulerProvider.computation())
    }

}