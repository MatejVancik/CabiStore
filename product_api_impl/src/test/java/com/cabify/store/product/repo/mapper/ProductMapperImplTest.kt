package com.cabify.store.product.repo.mapper

import com.cabify.store.product.mugProductData
import com.cabify.store.product.mugProductDto
import com.cabify.store.product.repo.data.mapper.ProductMapperImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductMapperImplTest {

    lateinit var mapper: ProductMapperImpl

    @Before
    fun setup() {
        mapper = ProductMapperImpl()
    }

    @Test
    fun `dto to product data`() {
        val actualResult = mapper.dtoToProductData(mugProductDto)

        assertEquals(actualResult, mugProductData)
    }
}