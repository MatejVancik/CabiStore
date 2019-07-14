package com.cabify.store.cart.presentation.data.mapper

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.presentation.data.CartItemDetailViewData
import com.cabify.store.core.extensions.toPrice

class CartItemDetailViewDataMapper {

    fun cartItemToDetailViewData(cartItemData: CartItemData) = with(cartItemData) {
        val finalPrice = discount?.invoke(this) ?: 0F
        val discount = count * pricePerItem - finalPrice

        CartItemDetailViewData(
            name,
            productImage,
            count,
            pricePerItem.toPrice(),
            discount.toPrice(),
            finalPrice.toPrice()
        )
    }

}