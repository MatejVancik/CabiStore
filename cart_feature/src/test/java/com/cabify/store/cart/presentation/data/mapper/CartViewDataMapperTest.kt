package com.cabify.store.cart.presentation.data.mapper

import com.cabify.store.cart.*
import com.cabify.store.cart.domain.data.CartData
import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.domain.data.Discount
import com.cabify.store.cart.presentation.data.CartItemViewData
import com.cabify.store.cart.presentation.data.CartViewData
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CartViewDataMapperTest {

    lateinit var mapper: CartViewDataMapper

    @Before
    fun setup() {
        mapper = CartViewDataMapper()
    }

    @Test
    fun `map cart to ViewData`() {
        val actualResult = mapper.mapCartToViewData(cartData)

        Assert.assertEquals(cartViewData, actualResult)
    }

}