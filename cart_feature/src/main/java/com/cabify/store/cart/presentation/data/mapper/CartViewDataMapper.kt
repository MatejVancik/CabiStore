package com.cabify.store.cart.presentation.data.mapper

import com.cabify.store.cart.domain.data.CartData
import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.presentation.data.CartItemViewData
import com.cabify.store.cart.presentation.data.CartViewData
import com.cabify.store.core.android.presentation.data.ProductResourceMapper
import com.cabify.store.core.extensions.toPrice

class CartViewDataMapper {

    fun mapCartToViewData(cartData: CartData): CartViewData {
        return CartViewData(
            cartData.items.map(::cartItemDataToViewData),
            cartData.fullPrice.toPrice(),
            cartData.discount.toPrice(),
            cartData.finalPrice.toPrice()
        )
    }

    private fun cartItemDataToViewData(cartItemData: CartItemData): CartItemViewData {
        val discount = cartItemData.discount?.invoke(cartItemData) ?: 0F
        val originalPrice = cartItemData.pricePerItem * cartItemData.count
        val finalPrice = originalPrice - discount

        val itemName = "${cartItemData.count}x ${cartItemData.name}"
        val originalPriceToShow = originalPrice.takeIf { discount > 0 }?.toPrice()

        return CartItemViewData(
            cartItemData.code.hashCode().toLong(),
            cartItemData.code,
            itemName,
            finalPrice.toPrice(),
            originalPriceToShow,
            ProductResourceMapper.getProductResource(cartItemData.code)
        )
    }

}