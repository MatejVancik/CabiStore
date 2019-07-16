package com.cabify.store.cart.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cabify.store.cart.cartData
import com.cabify.store.cart.cartViewData
import com.cabify.store.cart.domain.ObserveCartUseCase
import com.cabify.store.cart.presentation.data.mapper.CartViewDataMapper
import com.cabify.store.cart.presentation.viewmodel.CartViewModel
import com.cabify.store.core.android.presentation.viewdata.ViewData
import com.cabify.store.core.android.utils.AndroidSchedulerProviderTestImpl
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CartViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observeCartUseCase: ObserveCartUseCase

    @Mock
    lateinit var cartViewDataMapper: CartViewDataMapper

    private val schedulerProvider = AndroidSchedulerProviderTestImpl()

    private lateinit var viewModel: CartViewModel

    @Before
    fun setup() {
        viewModel = CartViewModel(observeCartUseCase, schedulerProvider, cartViewDataMapper)
    }

    @Test
    fun `load view data successfully`() {
        whenever(observeCartUseCase.observe()).thenReturn(Observable.just(cartData))
        whenever(cartViewDataMapper.mapCartToViewData(any())).thenReturn(cartViewData)

        Assert.assertTrue(viewModel.viewData.value is ViewData.Loading)
        viewModel.start()
        Assert.assertEquals((viewModel.viewData.value as ViewData.Data).content, cartViewData)
    }

    @Test
    fun `load view data error`() {
        whenever(observeCartUseCase.observe()).thenReturn(Observable.error(Error()))

        Assert.assertTrue(viewModel.viewData.value is ViewData.Loading)
        viewModel.start()
        Assert.assertTrue(viewModel.viewData.value is ViewData.Error)
    }

}