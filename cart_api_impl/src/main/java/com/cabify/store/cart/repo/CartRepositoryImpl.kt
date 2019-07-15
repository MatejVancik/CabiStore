package com.cabify.store.cart.repo

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.repo.data.CartItemDto
import com.cabify.store.cart.repo.data.mapper.CartMapper
import com.cabify.store.core.utils.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class CartRepositoryImpl(
    private val cartMapper: CartMapper,
    private val schedulerProvider: SchedulerProvider
) : CartRepository {

    private val cartData = mutableMapOf<String, CartItemDto>()

    private val dataSubject = BehaviorSubject.createDefault(listOf<CartItemData>())

    private fun notifyUpdate() {
        val newData = cartData.values.toList().map(cartMapper::dtoToCartItemData)
        dataSubject.onNext(newData)
    }

    override fun observeCartItems(): Observable<List<CartItemData>> {
        return dataSubject
    }

    override fun addToCart(productId: String, count: Int): Completable {
        return Completable.fromAction {
            val newCount = count + (cartData[productId]?.count ?: 0)
            cartData[productId] = CartItemDto(productId, newCount)
            notifyUpdate()
        }.subscribeOn(schedulerProvider.computation())
    }

    override fun getCartItem(productId: String): Single<CartItemData> {
        return Single.fromCallable {
            val item = cartData[productId] ?: throw IllegalArgumentException("Product not in cart!")
            cartMapper.dtoToCartItemData(item)
        }.subscribeOn(schedulerProvider.computation())
    }

    override fun updateCartItem(productId: String, count: Int): Completable {
        return Completable.fromAction {
            cartData[productId] = CartItemDto(productId, count)
            notifyUpdate()
        }.subscribeOn(schedulerProvider.computation())
    }

    override fun deleteCartItem(productId: String): Completable {
        return Completable.fromAction {
            cartData.remove(productId)
            notifyUpdate()
        }.subscribeOn(schedulerProvider.computation())
    }

}