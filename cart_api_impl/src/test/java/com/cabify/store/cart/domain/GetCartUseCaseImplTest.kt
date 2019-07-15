package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProviderTestImpl
import com.cabify.store.product.domain.GetAllProductsUseCase
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
class GetCartUseCaseImplTest {

    @Mock
    lateinit var cartRepository: CartRepository

    private val schedulerProvider = SchedulerProviderTestImpl()

    @Mock
    lateinit var getAllProductsUseCase: GetAllProductsUseCase

    private lateinit var getCartUseCaseImpl: GetCartUseCaseImpl

    private lateinit var cartItems: List<CartItemData>
    private lateinit var products: List<ProductData>

    @Before
    fun setup() {
        getCartUseCaseImpl = GetCartUseCaseImpl(cartRepository, schedulerProvider, getAllProductsUseCase)

        cartItems = listOf(CartItemData("MUG"))
        products = listOf(ProductData("TSHIRT", "T-shirt", 5F), ProductData("MUG", "Mug", 5F))
    }

    @Test
    fun `get cart items with products`() {
        whenever(cartRepository.getCartItems()).thenReturn(Single.just(cartItems))
        whenever(getAllProductsUseCase.get()).thenReturn(Single.just(products))

        getCartUseCaseImpl.get()
            .test()
            .assertNoErrors()
            .assertValue { it.items.size == 1 }

        verify(cartRepository, times(1)).getCartItems()
        verify(getAllProductsUseCase, times(1)).get()
    }

    @Test
    fun `get cart items with missing products`() {
        whenever(cartRepository.getCartItems()).thenReturn(Single.just(cartItems))
        whenever(getAllProductsUseCase.get()).thenReturn(Single.error(Error()))

        getCartUseCaseImpl.get()
            .test()
            .assertError { it is Error }

        verify(cartRepository, times(1)).getCartItems()
        verify(getAllProductsUseCase, times(1)).get()
    }

    @Test
    fun `get error cart items with products`() {
        whenever(cartRepository.getCartItems()).thenReturn(Single.error(Error()))
        whenever(getAllProductsUseCase.get()).thenReturn(Single.just(products))

        getCartUseCaseImpl.get()
            .test()
            .assertError { it is Error }

        verify(cartRepository, times(1)).getCartItems()
        verify(getAllProductsUseCase, times(1)).get()
    }

    @Test
    fun `get error cart items with missing products`() {
        whenever(cartRepository.getCartItems()).thenReturn(Single.error(Error()))
        whenever(getAllProductsUseCase.get()).thenReturn(Single.error(Error()))

        getCartUseCaseImpl.get()
            .test()
            .assertError { it is Error }

        verify(cartRepository, times(1)).getCartItems()
        verify(getAllProductsUseCase, times(1)).get()
    }

}