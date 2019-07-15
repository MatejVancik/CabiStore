package com.cabify.store.product.domain

import com.cabify.store.core.utils.SchedulerProviderTestImpl
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.domain.data.ProductDiscount
import com.cabify.store.product.repo.ProductRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllProductsUseCaseImplTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    private val schedulerProvider = SchedulerProviderTestImpl()

    private lateinit var getAllProductsUseCase: GetAllProductsUseCaseImpl

    @Before
    fun setup() {
        getAllProductsUseCase = GetAllProductsUseCaseImpl(productRepository, schedulerProvider)
    }

    @Test
    fun `get list of products`() {
        val tshirt = ProductData("TSHIRT", "Tshirt", 20F)
        val voucher = ProductData("VOUCHER", "Voucher", 5F)
        val mug = ProductData("MUG", "Mug", 7F)
        val originalList = listOf(tshirt, voucher, mug)

        val resultingList = listOf(
            tshirt.copy(discountType = ProductDiscount.DiscountOnThree),
            voucher.copy(discountType = ProductDiscount.OnePlusOne),
            mug
        )

        whenever(productRepository.getProducts()).thenReturn(Single.just(originalList))

        getAllProductsUseCase.get()
            .test()
            .assertNoErrors()
            .assertValue { it == resultingList }
    }

    @Test
    fun `error on getting list of products`() {
        whenever(productRepository.getProducts()).thenReturn(Single.error(Error()))

        getAllProductsUseCase.get()
            .test()
            .assertError { it is Error }
    }

}