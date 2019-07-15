package com.cabify.store.cart.domain

import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProviderTestImpl
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddItemToCartUseCaseImplTest {

    @Mock
    lateinit var cartRepository: CartRepository

    private val schedulerProvider = SchedulerProviderTestImpl()

    private lateinit var addItemToCartUseCase: AddItemToCartUseCase

    @Before
    fun setup() {
        addItemToCartUseCase = AddItemToCartUseCaseImpl(cartRepository, schedulerProvider)
    }

    @Test
    fun `add product to cart`() {
        whenever(cartRepository.addToCart(any(), any())).thenReturn(Completable.complete())

        addItemToCartUseCase.add("TSHIRT", 3)
            .test()
            .assertNoErrors()
            .assertComplete()

        verify(cartRepository, times(1)).addToCart("TSHIRT", 3)
    }

}