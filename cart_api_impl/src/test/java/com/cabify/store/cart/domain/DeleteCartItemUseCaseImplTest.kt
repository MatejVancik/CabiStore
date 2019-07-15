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
class DeleteCartItemUseCaseImplTest {

    @Mock
    lateinit var cartRepository: CartRepository

    private val schedulerProvider = SchedulerProviderTestImpl()

    private lateinit var deleteCartItemUseCase: DeleteCartItemUseCase

    @Before
    fun setup() {
        deleteCartItemUseCase = DeleteCartItemUseCaseImpl(cartRepository, schedulerProvider)
    }

    @Test
    fun `delete existing item`() {
        whenever(cartRepository.deleteCartItem("TSHIRT")).thenReturn(Completable.complete())

        deleteCartItemUseCase.delete("TSHIRT")
            .test()
            .assertNoErrors()
            .assertComplete()

        verify(cartRepository, times(1)).deleteCartItem("TSHIRT")
    }

    @Test
    fun `delete item not in cart`() {
        whenever(cartRepository.deleteCartItem("PLANE")).thenReturn(Completable.error(IllegalArgumentException()))

        deleteCartItemUseCase.delete("PLANE")
            .test()
            .assertError { it is IllegalArgumentException }

        verify(cartRepository, times(1)).deleteCartItem("PLANE")
    }
}