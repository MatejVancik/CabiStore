package com.cabify.store.product.repo

import com.cabify.store.core.utils.SchedulerProviderTestImpl
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.repo.data.ProductDto
import com.cabify.store.product.repo.data.ProductListDto
import com.cabify.store.product.repo.data.mapper.ProductMapper
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductRepositoryImplTest {

    @Mock
    lateinit var productRemoteApi: ProductRemoteApi

    @Mock
    lateinit var productMapper: ProductMapper

    private val schedulerProvider = SchedulerProviderTestImpl()

    private lateinit var repo: ProductRepositoryImpl

    @Before
    fun setup() {
        repo = ProductRepositoryImpl(productRemoteApi, schedulerProvider, productMapper)
    }

    @Test
    fun `get products successfully`() {
        val mugDto = ProductDto("MUG", "Mug", 7f)
        val dto = ProductListDto(listOf(mugDto))

        val mugData = ProductData("MUG", "Mug", 7f)
        val products = listOf(mugData)

        whenever(productRemoteApi.getProductsList()).thenReturn(Single.just(dto))
        whenever(productMapper.dtoToProductData(mugDto)).thenReturn(mugData)

        repo.getProducts()
            .test()
            .assertNoErrors()
            .assertValue { it == products }
    }

    @Test
    fun `get products error`() {
        whenever(productRemoteApi.getProductsList()).thenReturn(Single.error(Error()))

        repo.getProducts()
            .test()
            .assertError { it is Error }
    }

}