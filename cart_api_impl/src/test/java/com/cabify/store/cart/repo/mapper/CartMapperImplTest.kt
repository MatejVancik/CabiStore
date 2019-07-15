package com.cabify.store.cart.repo.mapper

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.repo.data.CartItemDto
import com.cabify.store.cart.repo.data.mapper.CartMapperImpl
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals as assertEquals

class CartMapperImplTest {

    lateinit var mapperUnderTest: CartMapperImpl

    @Before
    fun setup() {
        mapperUnderTest = CartMapperImpl()
    }

    @Test
    fun `map dto to CartItemData successfully`() {
        val dto = CartItemDto("MUG", 2)
        val result = mapperUnderTest.dtoToCartItemData(dto)
        assertEquals(dto.code, result.code)
        assertEquals(dto.count, result.count)
    }

}