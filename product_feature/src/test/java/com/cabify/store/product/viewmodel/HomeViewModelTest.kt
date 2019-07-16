package com.cabify.store.product.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cabify.store.core.android.presentation.viewdata.ViewData
import com.cabify.store.core.android.utils.AndroidSchedulerProviderTestImpl
import com.cabify.store.product.domain.GetAllProductsUseCase
import com.cabify.store.product.mugHomeProductItemViewData
import com.cabify.store.product.mugProductData
import com.cabify.store.product.presentation.data.HomeViewData
import com.cabify.store.product.presentation.data.mapper.HomeViewDataMapper
import com.cabify.store.product.presentation.viewmodel.HomeViewModel
import com.cabify.store.product.voucherHomeProductItemViewData
import com.cabify.store.product.voucherProductData
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getAllProductsUseCase: GetAllProductsUseCase

    @Mock
    lateinit var homeViewDataMapper: HomeViewDataMapper

    private val schedulerProvider = AndroidSchedulerProviderTestImpl()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(getAllProductsUseCase, schedulerProvider, homeViewDataMapper)
    }

    @Test
    fun `load view data successfully`() {
        val productData = listOf(mugProductData, voucherProductData)
        val expectedViewData = HomeViewData(listOf(mugHomeProductItemViewData, voucherHomeProductItemViewData))

        whenever(getAllProductsUseCase.get()).thenReturn(Single.just(productData))
        whenever(homeViewDataMapper.mapProductDataToViewData(productData)).thenReturn(expectedViewData)

        Assert.assertTrue(viewModel.viewData.value is ViewData.Loading)
        viewModel.start()
        Assert.assertEquals(expectedViewData, (viewModel.viewData.value as ViewData.Data).content)
    }

    @Test
    fun `load view data error`() {
        whenever(getAllProductsUseCase.get()).thenReturn(Single.error(Error()))

        Assert.assertTrue(viewModel.viewData.value is ViewData.Loading)
        viewModel.start()
        Assert.assertTrue(viewModel.viewData.value is ViewData.Error)
    }

}