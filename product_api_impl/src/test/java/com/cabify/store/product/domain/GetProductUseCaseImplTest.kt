package com.cabify.store.product.domain

import com.cabify.store.core.utils.SchedulerProviderTestImpl
import com.cabify.store.product.domain.data.ProductData
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetProductUseCaseImplTest {

    @Mock
    private lateinit var getAllProductsUseCase: GetAllProductsUseCase

    private val schedulerProvider = SchedulerProviderTestImpl()

    private lateinit var getProductUseCase: GetProductUseCaseImpl

    @Before
    fun setup() {
        getProductUseCase = GetProductUseCaseImpl(getAllProductsUseCase, schedulerProvider)
    }

    @Test
    fun `get existing product`() {
        val tshirt = ProductData("TSHIRT", "Tshirt", 20F)
        val mug = ProductData("MUG", "Mug", 5F)
        whenever(getAllProductsUseCase.get()).thenReturn(Single.just(listOf(tshirt, mug)))

        getProductUseCase.getProduct("TSHIRT")
            .test()
            .assertNoErrors()
            .assertValue { it == tshirt }
    }

    @Test
    fun `get missing product`() {
        whenever(getAllProductsUseCase.get()).thenReturn(Single.just(listOf()))

        getProductUseCase.getProduct("MUG")
            .test()
            .assertError { it is IllegalArgumentException }
    }

}