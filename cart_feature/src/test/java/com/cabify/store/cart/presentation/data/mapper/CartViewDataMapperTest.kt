package com.cabify.store.cart.presentation.data.mapper

import com.cabify.store.cart.R
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
        val mug = CartItemData("MUG", "Mug", 7f, 2)
        val voucher = CartItemData("VOUCHER", "Voucher", 5f, 2, Discount.OnePlusOne)
        val cartData = CartData(listOf(mug, voucher), 24f, 5f)

        val mugViewData = CartItemViewData(
            identifier = "MUG".hashCode().toLong(),
            code = "MUG",
            title = "2x Mug",
            finalPrice = "14.00€",
            originalPrice = null,
            image = R.drawable.product_mug_small
        )

        val voucherViewData = CartItemViewData(
            identifier = "VOUCHER".hashCode().toLong(),
            code = "VOUCHER",
            title = "2x Voucher",
            finalPrice = "5.00€",
            originalPrice = "10.00€",
            image = R.drawable.product_vouche_small
        )

        val expectedResult = CartViewData(listOf(mugViewData, voucherViewData), "24.00€", "5.00€", "19.00€")
        val actualResult = mapper.mapCartToViewData(cartData)

        Assert.assertEquals(expectedResult, actualResult)
    }

}