package com.cabify.store.cart.presentation.data.mapper

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.presentation.data.CartItemDetailViewData
import com.cabify.store.core.android.presentation.data.ProductResourceMapper
import com.cabify.store.core.extensions.toPrice

class CartItemDetailViewDataMapper {

    fun cartItemToDetailViewData(cartItemData: CartItemData) = with(cartItemData) {
        val discount = discount?.invoke(this) ?: 0F
        val finalPrice = count * pricePerItem - discount

        CartItemDetailViewData(
            name,
            ProductResourceMapper.getProductResource(cartItemData.code),
            count,
            pricePerItem.toPrice(),
            discount.toPrice(),
            finalPrice.toPrice()
        )
    }

}