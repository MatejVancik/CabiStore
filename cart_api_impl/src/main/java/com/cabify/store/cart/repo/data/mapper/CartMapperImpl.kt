package com.cabify.store.cart.repo.data.mapper

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.repo.data.CartItemDto

class CartMapperImpl : CartMapper {

    override fun dtoToCartItemData(dto: CartItemDto): CartItemData {
        return CartItemData(
            code = dto.code,
            count = dto.count
        )
    }

}