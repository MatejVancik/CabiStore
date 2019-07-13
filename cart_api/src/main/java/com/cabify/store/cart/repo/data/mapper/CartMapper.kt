package com.cabify.store.cart.repo.data.mapper

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.repo.data.CartItemDto

interface CartMapper {

    fun dtoToCartItemData(dto: CartItemDto): CartItemData

}