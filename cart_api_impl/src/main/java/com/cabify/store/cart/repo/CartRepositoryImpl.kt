package com.cabify.store.cart.repo

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.repo.data.CartItemDto
import com.cabify.store.cart.repo.data.mapper.CartMapper
import com.cabify.store.core.utils.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single

class CartRepositoryImpl(
    private val cartMapper: CartMapper,
    private val schedulerProvider: SchedulerProvider
): CartRepository {

    private val cartData = mutableMapOf<String, CartItemDto>()

    override fun getCartItems(): Single<List<CartItemData>> {
        return Single.fromCallable { cartData.values.toList().map(cartMapper::dtoToCartItemData) }
            .subscribeOn(schedulerProvider.computation())
    }

    override fun addToCart(productId: String, count: Int): Completable {
        return Completable.fromAction { cartData[productId] = CartItemDto(productId, count) }
    }

    override fun updateCartItem(cartItem: CartItemData): Completable {
        // Simple implementation which simply replaces (or adds) existing item in current cart. More advance
        // repository implementations can do more, e.g. throw an error if item doesn't exist yet or delete an item
        // if count of items in cartItemData is 0.
        return addToCart(cartItem.code, cartItem.count)
    }

    override fun deleteCartItem(cartItem: CartItemData): Completable {
        return Completable.fromAction { cartData.remove(cartItem.code) }
    }

}