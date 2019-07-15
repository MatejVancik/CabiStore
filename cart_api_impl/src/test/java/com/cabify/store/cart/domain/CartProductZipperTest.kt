package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartData
import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.domain.data.Discount
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.domain.data.ProductDiscount
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class CartProductZipperTest {

    private val oneItem = listOf(CartItemData("MUG", "mug", 7f, 3))
    private val twoItems = listOf(CartItemData("MUG", "mug", 7f, 3), CartItemData("TSHIRT", "T-shirt", 20f, 3))

    private val noProducts = listOf<ProductData>()
    private val allProducts = listOf(
        ProductData("MUG", "Mug", 6f), ProductData("TSHIRT", "T-shirt", 20f, ProductDiscount.DiscountOnThree)
    )
    private val emptyCartResult = CartData(listOf(), 0F, 0F)

    private lateinit var zipperUnderTest: CartProductZipper

    @Before
    fun setup() {
        zipperUnderTest = CartProductZipper()
    }

    @Test
    fun `zip one with existing product`() {
        val oneItemResult = CartData(listOf(CartItemData("MUG", "Mug", 6f, 3)), 18f, 0f)

        val zippedResult = zipperUnderTest.apply(oneItem, allProducts)
        assertEquals(zippedResult, oneItemResult)
    }

    @Test
    fun `zip two with existing products`() {
        val twoItemsRsult = CartData(listOf(
            CartItemData("MUG", "Mug", 6f, 3),
            CartItemData("TSHIRT", "T-shirt", 20f, 3, Discount.DiscountOnThree)
        ), 78f, 3f)

        val zippedResult = zipperUnderTest.apply(twoItems, allProducts)
        assertEquals(zippedResult, twoItemsRsult)
    }

    @Test
    fun `zip no items`() {
        val zippedResult = zipperUnderTest.apply(listOf(), noProducts)
        assertEquals(zippedResult, emptyCartResult)
    }

    @Test
    fun `zip one with missing product`() {
        val zippedResult = zipperUnderTest.apply(oneItem, noProducts)
        assertEquals(zippedResult, emptyCartResult)
    }

}