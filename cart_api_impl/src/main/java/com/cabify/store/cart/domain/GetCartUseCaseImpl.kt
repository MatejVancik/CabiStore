package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartData
import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.domain.GetAllProductsUseCase
import io.reactivex.Single

class GetCartUseCaseImpl(
    private val cartRepository: CartRepository,
    private val schedulerProvider: SchedulerProvider,
    private val getAllProductsUseCase: GetAllProductsUseCase
) : GetCartUseCase {

    override fun get(): Single<CartData> {
        return Single.zip(cartRepository.getCartItems(), getAllProductsUseCase.get(), CartProductZipper())
            .observeOn(schedulerProvider.computation())
    }

}