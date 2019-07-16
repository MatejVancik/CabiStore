package com.cabify.store.cart.repo.mapper

import com.cabify.store.cart.mugCartItemDto
import com.cabify.store.cart.repo.data.CartItemDto
import com.cabify.store.cart.repo.data.mapper.CartMapperImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CartMapperImplTest {

    lateinit var mapperUnderTest: CartMapperImpl

    @Before
    fun setup() {
        mapperUnderTest = CartMapperImpl()
    }

    @Test
    fun `map dto to CartItemData successfully`() {
        val result = mapperUnderTest.dtoToCartItemData(mugCartItemDto)
        assertEquals(mugCartItemDto.code, result.code)
        assertEquals(mugCartItemDto.count, result.count)
    }

}