package com.cabify.store.cart.presentation.data.mapper

import com.cabify.store.cart.R
import com.cabify.store.cart.domain.data.CartData
import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.presentation.data.CartItemViewData
import com.cabify.store.cart.presentation.data.CartViewData
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
        val totalPrice = cartItemData.pricePerItem * cartItemData.count
        val discount = cartItemData.discount?.invoke(cartItemData)

        return CartItemViewData(
            cartItemData.code.hashCode().toLong(),
            cartItemData.code,
            "${cartItemData.count}x ${cartItemData.name}",
            totalPrice.toPrice(),
            discount?.toPrice(),
            getProductResource(cartItemData.code)
        )
    }

    // Copied from com.cabify.store.product.presentation.data.mapper.HomeViewDataMapper.
    // In real world, where image would be represented by URL this would come from DomainModel at this point.
    private fun getProductResource(productId: String) = when (productId) {
        "TSHIRT" -> R.drawable.product_tshirt_small
        "MUG" -> R.drawable.product_mug_small
        "VOUCHER" -> R.drawable.product_vouche_small
        else -> R.drawable.product_default
    }

}