package com.cabify.store.cart.domain

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.mugCartItemData
import com.cabify.store.cart.mugProductData
import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.cart.tshirtCartItemData
import com.cabify.store.cart.tshirtProductData
import com.cabify.store.core.utils.SchedulerProviderTestImpl
import com.cabify.store.product.domain.GetAllProductsUseCase
import com.cabify.store.product.domain.data.ProductData
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ObserveCartUseCaseImplTest {

    @Mock
    lateinit var cartRepository: CartRepository

    private val schedulerProvider = SchedulerProviderTestImpl()

    @Mock
    lateinit var getAllProductsUseCaseImpl: GetAllProductsUseCase

    private lateinit var observeCartUseCase: ObserveCartUseCaseImpl

    private lateinit var cartItemsFirst: List<CartItemData>
    private lateinit var cartItemsSecond: List<CartItemData>

    private lateinit var products: List<ProductData>

    @Before
    fun setup() {
        observeCartUseCase = ObserveCartUseCaseImpl(cartRepository, getAllProductsUseCaseImpl, schedulerProvider)

        cartItemsFirst = listOf(mugCartItemData)
        cartItemsSecond = listOf(mugCartItemData, tshirtCartItemData)

        products = listOf(tshirtProductData, mugProductData)
    }

    @Test
    fun `observe single event`() {
        whenever(cartRepository.observeCartItems()).thenReturn(Observable.just(cartItemsFirst))
        whenever(getAllProductsUseCaseImpl.get()).thenReturn(Single.just(products))

        observeCartUseCase.observe()
            .test()
            .assertNoErrors()
            .assertValueCount(1)

        verify(cartRepository, times(1)).observeCartItems()
        verify(getAllProductsUseCaseImpl, times(1)).get()
    }

    @Test
    fun `observe multiple events`() {
        val publishSubject = PublishSubject.create<List<CartItemData>>()
        whenever(cartRepository.observeCartItems()).thenReturn(publishSubject)
        whenever(getAllProductsUseCaseImpl.get()).thenReturn(Single.just(products))

        var values = 0

        observeCartUseCase.observe()
            .subscribe { values++ }

        publishSubject.onNext(cartItemsFirst)
        publishSubject.onNext(cartItemsSecond)

        verify(cartRepository, times(1)).observeCartItems()
        verify(getAllProductsUseCaseImpl, times(1)).get()
        Assert.assertEquals(2, values)
    }

}