package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProviderTestImpl
import com.cabify.store.product.domain.GetProductUseCase
import com.cabify.store.product.domain.data.ProductData
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCartItemUseCaseImplTest {

    @Mock
    lateinit var cartRepository: CartRepository

    @Mock
    lateinit var getProductUseCase: GetProductUseCase

    private val schedulerProvider = SchedulerProviderTestImpl()

    private lateinit var getCartItemUseCase: GetCartItemUseCase
    private lateinit var mugProduct: ProductData
    private lateinit var mugCartItem: CartItemData

    @Before
    fun setup() {
        getCartItemUseCase = GetCartItemUseCaseImpl(cartRepository, getProductUseCase, schedulerProvider)
        mugProduct = ProductData("MUG", "Mug", 5f, null)
        mugCartItem = CartItemData("MUG", "Mug", 5F, 2)
    }

    @Test
    fun `get existing cart item and product`() {
        whenever(getProductUseCase.getProduct("MUG")).thenReturn(Single.just(mugProduct))
        whenever(cartRepository.getCartItem("MUG")).thenReturn(Single.just(mugCartItem))

        getCartItemUseCase.get("MUG")
            .test()
            .assertNoErrors()
            .assertValue { it.code == "MUG" }

        verify(cartRepository, times(1)).getCartItem("MUG")
        verify(getProductUseCase, times(1)).getProduct("MUG")
    }

    @Test
    fun `get existing cart item with missing product`() {
        whenever(getProductUseCase.getProduct("MUG")).thenReturn(Single.error(Error()))
        whenever(cartRepository.getCartItem("MUG")).thenReturn(Single.just(mugCartItem))

        getCartItemUseCase.get("MUG")
            .test()
            .assertError { it is Error }

        verify(cartRepository, times(1)).getCartItem("MUG")
        verify(getProductUseCase, times(1)).getProduct("MUG")
    }

    @Test
    fun `get missing cart item with existing product`() {
        whenever(getProductUseCase.getProduct("MUG")).thenReturn(Single.just(mugProduct))
        whenever(cartRepository.getCartItem("MUG")).thenReturn(Single.error(Error()))

        getCartItemUseCase.get("MUG")
            .test()
            .assertError { it is Error }

        verify(cartRepository, times(1)).getCartItem("MUG")
        verify(getProductUseCase, times(1)).getProduct("MUG")
    }

    @Test
    fun `get missing cart item and missing product`() {
        whenever(getProductUseCase.getProduct("MUG")).thenReturn(Single.error(Error()))
        whenever(cartRepository.getCartItem("MUG")).thenReturn(Single.error(Error()))

        getCartItemUseCase.get("MUG")
            .test()
            .assertError { it is Error }

        verify(cartRepository, times(1)).getCartItem("MUG")
        verify(getProductUseCase, times(1)).getProduct("MUG")
    }

}