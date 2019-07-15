package com.cabify.store.cart.ext

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.domain.data.Discount
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.domain.data.ProductDiscount
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductDataExtTest {

    @Test
    fun `bind product data to cart item`() {
        val cartItem = CartItemData("MUG", count = 4)
        val productData = ProductData("MUG", "Mug", 5F, ProductDiscount.DiscountOnThree)

        val result = productData.bindToCartItem(cartItem)

        result.run {
            assertEquals(code, "MUG")
            assertEquals(name, "Mug")
            assertEquals(pricePerItem, 5F)
            assertEquals(count, 4)
            assertEquals(discount, Discount.DiscountOnThree)
        }
    }

}