package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.ext.bindToCartItem
import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.domain.GetProductUseCase
import com.cabify.store.product.domain.data.ProductData
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GetCartItemUseCaseImpl(
    private val cartRepository: CartRepository,
    private val getProductUseCase: GetProductUseCase,
    private val schedulerProvider: SchedulerProvider
) : GetCartItemUseCase {

    override fun get(productId: String): Single<CartItemData> {
        return Single.zip(
            cartRepository.getCartItem(productId),
            getProductUseCase.getProduct(productId),
            productBinding()
        ).observeOn(schedulerProvider.computation())
    }

    private fun productBinding() = BiFunction { cartItem: CartItemData, product: ProductData ->
        product.bindToCartItem(cartItem)
    }

}