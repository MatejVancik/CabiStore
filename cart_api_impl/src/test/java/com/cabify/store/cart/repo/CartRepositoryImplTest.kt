package com.cabify.store.cart.repo

import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.repo.data.CartItemDto
import com.cabify.store.cart.repo.data.mapper.CartMapper
import com.cabify.store.core.utils.SchedulerProviderTestImpl
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalArgumentException

@RunWith(MockitoJUnitRunner::class)
class CartRepositoryImplTest {

    @Mock
    lateinit var mapper: CartMapper

    private val schedulerProvider = SchedulerProviderTestImpl()

    private lateinit var repo: CartRepositoryImpl

    private lateinit var mugDto: CartItemDto
    private lateinit var tshirtDto: CartItemDto

    private lateinit var mugData: CartItemData
    private lateinit var tshirtData: CartItemData

    @Before
    fun setup() {
        repo = CartRepositoryImpl(mapper, schedulerProvider)

        mugDto = CartItemDto("MUG", 4)
        mugData = CartItemData("MUG", count = 4)

        tshirtDto = CartItemDto("TSHIRT", 3)
        tshirtData = CartItemData("TSHIRT", count = 3)
    }

    // Note: Following tests are more integration than unit tests. Due to simple implementation of the repository
    // which is not using any external source or persistence of data we need to call multiple functions to fulfill
    // preconditions for the tests. E.g. add items to repository before reading them. Unit testing separate methods
    // in this case is not possible.

    @Test
    fun `test observe`() {
        whenever(mapper.dtoToCartItemData(mugDto)).thenReturn(mugData)
        whenever(mapper.dtoToCartItemData(tshirtDto)).thenReturn(tshirtData)

        val results = mutableListOf<List<CartItemData>>()
        repo.observeCartItems()
            .subscribe { results.add(it) }

        repo.addToCart("MUG", 4).subscribe()
        repo.addToCart("TSHIRT", 3).subscribe()

        assertEquals(results[0], listOf<CartItemData>())
        assertEquals(results[1], listOf(mugData))
        assertEquals(results[2], listOf(mugData, tshirtData))
    }

    @Test
    fun `get existing item`() {
        whenever(mapper.dtoToCartItemData(mugDto)).thenReturn(mugData)
        repo.addToCart("MUG", 4).subscribe()

        repo.getCartItem("MUG")
            .test()
            .assertNoErrors()
            .assertValue { it == mugData }
    }

    @Test
    fun `get missing item`() {
        repo.getCartItem("MUG")
            .test()
            .assertError { it is IllegalArgumentException }
    }

    @Test
    fun `update item`() {
        whenever(mapper.dtoToCartItemData(mugDto)).thenReturn(mugData)
        repo.addToCart("MUG", 1).subscribe()

        repo.updateCartItem("MUG", 4)
            .test()
            .assertNoErrors()
            .assertComplete()

        repo.getCartItem("MUG")
            .test()
            .assertNoErrors()
            .assertValue { it == mugData }
    }

    @Test
    fun `delete item`() {
        whenever(mapper.dtoToCartItemData(mugDto)).thenReturn(mugData)
        repo.addToCart("MUG", 4).subscribe()

        repo.getCartItem("MUG")
            .test()
            .assertNoErrors()
            .assertValue { it == mugData }

        repo.deleteCartItem("MUG")
            .test()
            .assertNoErrors()
            .assertComplete()

        repo.getCartItem("MUG")
            .test()
            .assertError { it is IllegalArgumentException }
    }

}