package com.cabify.store.cart.domain

import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.core.utils.SchedulerProviderTestImpl
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
class UpdateCartItemUseCaseImplTest {

    @Mock
    lateinit var cartRepository: CartRepository

    private val schedulerProvider = SchedulerProviderTestImpl()

    private lateinit var updateCartItemUseCase: UpdateCartItemUseCaseImpl

    @Before
    fun setup() {
        updateCartItemUseCase = UpdateCartItemUseCaseImpl(cartRepository, schedulerProvider)
    }

    @Test
    fun `update product successfully`() {
        whenever(cartRepository.updateCartItem("MUG", 1)).thenReturn(Completable.complete())

        updateCartItemUseCase.update("MUG", 1)
            .test()
            .assertNoErrors()
            .assertComplete()

        verify(cartRepository, times(1)).updateCartItem("MUG", 1)
    }

    @Test
    fun `update product with error`() {
        whenever(cartRepository.updateCartItem("MUG", 1)).thenReturn(Completable.error(Error()))

        updateCartItemUseCase.update("MUG", 1)
            .test()
            .assertError { it is Error }

        verify(cartRepository, times(1)).updateCartItem("MUG", 1)
    }

}