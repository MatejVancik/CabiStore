package com.cabify.store.cart.presentation.data.mapper

import com.cabify.store.cart.mugCartItemData
import com.cabify.store.cart.mugCartItemDetailViewData
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CartItemDetailViewDataMapperTest {

    private lateinit var mapper: CartItemDetailViewDataMapper

    @Before
    fun setup() {
        mapper = CartItemDetailViewDataMapper()
    }

    @Test
    fun `cart item to detail ViewData`() {
        val actualResult = mapper.cartItemToDetailViewData(mugCartItemData)

        Assert.assertEquals(mugCartItemDetailViewData, actualResult)
    }

}