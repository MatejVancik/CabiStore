package com.cabify.store.cart.repo

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.mugCartItemData
import com.cabify.store.cart.mugCartItemDto
import com.cabify.store.cart.repo.data.mapper.CartMapper
import com.cabify.store.cart.tshirtCartItemData
import com.cabify.store.cart.tshirtCartItemDto
import com.cabify.store.core.utils.SchedulerProviderTestImpl
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CartRepositoryImplTest {

    @Mock
    lateinit var mapper: CartMapper

    private val schedulerProvider = SchedulerProviderTestImpl()

    private lateinit var repo: CartRepositoryImpl

    @Before
    fun setup() {
        repo = CartRepositoryImpl(mapper, schedulerProvider)
    }

    // Note: Following tests are more integration than unit tests. Due to simple implementation of the repository
    // which is not using any external source or persistence of data we need to call multiple functions to fulfill
    // preconditions for the tests. E.g. add items to repository before reading them. Unit testing separate methods
    // in this case is not possible.

    @Test
    fun `test observe`() {
        whenever(mapper.dtoToCartItemData(mugCartItemDto)).thenReturn(mugCartItemData)
        whenever(mapper.dtoToCartItemData(tshirtCartItemDto)).thenReturn(tshirtCartItemData)

        val results = mutableListOf<List<CartItemData>>()
        repo.observeCartItems()
            .subscribe { results.add(it) }

        repo.addToCart("MUG", 2).subscribe()
        repo.addToCart("TSHIRT", 3).subscribe()

        assertEquals(results[0], listOf<CartItemData>())
        assertEquals(results[1], listOf(mugCartItemData))
        assertEquals(results[2], listOf(mugCartItemData, tshirtCartItemData))
    }

    @Test
    fun `get existing item`() {
        whenever(mapper.dtoToCartItemData(mugCartItemDto)).thenReturn(mugCartItemData)
        repo.addToCart("MUG", 2).subscribe()

        repo.getCartItem("MUG")
            .test()
            .assertNoErrors()
            .assertValue { it == mugCartItemData }
    }

    @Test
    fun `get missing item`() {
        repo.getCartItem("MUG")
            .test()
            .assertError { it is IllegalArgumentException }
    }

    @Test
    fun `update item`() {
        whenever(mapper.dtoToCartItemData(mugCartItemDto)).thenReturn(mugCartItemData)
        repo.addToCart("MUG", 1).subscribe()

        repo.updateCartItem("MUG", 2)
            .test()
            .assertNoErrors()
            .assertComplete()

        repo.getCartItem("MUG")
            .test()
            .assertNoErrors()
            .assertValue { it == mugCartItemData }
    }

    @Test
    fun `delete item`() {
        whenever(mapper.dtoToCartItemData(mugCartItemDto)).thenReturn(mugCartItemData)
        repo.addToCart("MUG", 2).subscribe()

        repo.getCartItem("MUG")
            .test()
            .assertNoErrors()
            .assertValue { it == mugCartItemData }

        repo.deleteCartItem("MUG")
            .test()
            .assertNoErrors()
            .assertComplete()

        repo.getCartItem("MUG")
            .test()
            .assertError { it is IllegalArgumentException }
    }

}