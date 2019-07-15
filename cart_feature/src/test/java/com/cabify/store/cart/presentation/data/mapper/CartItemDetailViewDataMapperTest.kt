package com.cabify.store.cart.presentation.data.mapper

import com.cabify.store.cart.R
import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.domain.data.Discount
import com.cabify.store.cart.presentation.data.CartItemDetailViewData
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
        val data = CartItemData("MUG", "Mug", 7f, 1, Discount.OnePlusOne)
        val expectedResult = CartItemDetailViewData("Mug", R.drawable.product_mug_small, 1, "7.00€", "0.00€", "7.00€")
        val actualResult = mapper.cartItemToDetailViewData(data)

        Assert.assertEquals(expectedResult, actualResult)
    }

}