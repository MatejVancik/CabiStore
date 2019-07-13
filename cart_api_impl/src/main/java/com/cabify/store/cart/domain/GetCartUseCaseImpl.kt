package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartData
import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.domain.data.Discount
import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.domain.GetAllProductsUseCase
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.domain.data.ProductDiscount
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GetCartUseCaseImpl(
    private val cartRepository: CartRepository,
    private val schedulerProvider: SchedulerProvider,
    private val getAllProductsUseCase: GetAllProductsUseCase
) : GetCartUseCase {

    override fun get(): Single<CartData> {
        return Single.zip(cartRepository.getCartItems(), getAllProductsUseCase.get(), getProductCartZip())
            .observeOn(schedulerProvider.computation())
    }

    private fun getProductCartZip() = BiFunction { cartItems: List<CartItemData>, products: List<ProductData> ->
        val updatedCartItems = cartItems.mapNotNull { cartItem ->
            products.find { it.code == cartItem.code }
                ?.bindToCartItem(cartItem)
        }

        buildCartData(updatedCartItems)
    }

    private fun ProductData.bindToCartItem(originalItem: CartItemData): CartItemData {
        return CartItemData(originalItem.code, name, price, originalItem.count, discountType?.toCartDiscount())
    }

    private fun buildCartData(items: List<CartItemData>): CartData {
        val fullPrice = items.map { it.pricePerItem * it.count }.reduce { acc, price -> acc + price }
        val discountedPrice = items.mapNotNull { it.discount?.invoke(it) }.reduce { acc, discount -> acc + discount }
        val discount = fullPrice - discountedPrice

        return CartData(items, fullPrice, discount)
    }

    private fun ProductDiscount.toCartDiscount() = when (this) {
        ProductDiscount.OnePlusOne -> Discount.OnePlusOne
        ProductDiscount.DiscountOnThree -> Discount.DiscountOnThree
    }

}