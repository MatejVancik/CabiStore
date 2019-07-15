package com.cabify.store.product.repo.mapper

import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.repo.data.ProductDto
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
        val dto = ProductDto("MUG", "Mug", 7f)
        val expectedResult = ProductData("MUG", "Mug", 7f)

        val actualResult = mapper.dtoToProductData(dto)

        assertEquals(actualResult, expectedResult)
    }
}