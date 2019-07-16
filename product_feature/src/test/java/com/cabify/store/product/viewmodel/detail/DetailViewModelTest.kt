package com.cabify.store.product.viewmodel.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cabify.store.cart.domain.AddItemToCartUseCase
import com.cabify.store.core.android.presentation.viewdata.ViewData
import com.cabify.store.core.android.utils.AndroidSchedulerProviderTestImpl
import com.cabify.store.product.domain.GetProductUseCase
import com.cabify.store.product.mugProductData
import com.cabify.store.product.mugProductDetailViewData
import com.cabify.store.product.presentation.data.mapper.detail.ProductDetailViewDataMapper
import com.cabify.store.product.presentation.viewmodel.detail.DetailViewModel
import com.nhaarman.mockitokotlin2.any
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
class DetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getProductUseCase: GetProductUseCase

    @Mock
    lateinit var detailViewDataMapper: ProductDetailViewDataMapper

    @Mock
    lateinit var addItemToCartUseCase: AddItemToCartUseCase

    private val schedulerProvider = AndroidSchedulerProviderTestImpl()

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        viewModel = DetailViewModel(
            getProductUseCase,
            detailViewDataMapper,
            addItemToCartUseCase,
            schedulerProvider
        )
    }

    @Test
    fun `load view data successfully`() {
        whenever(getProductUseCase.getProduct("MUG")).thenReturn(Single.just(mugProductData))
        whenever(detailViewDataMapper.productToDetailViewData(any())).thenReturn(mugProductDetailViewData)

        Assert.assertTrue(viewModel.viewData.value is ViewData.Loading)
        viewModel.start("MUG")
        Assert.assertEquals(mugProductDetailViewData, (viewModel.viewData.value as ViewData.Data).content)
    }

    @Test
    fun `load view data error`() {
        whenever(getProductUseCase.getProduct("MUG")).thenReturn(Single.error(Error()))

        Assert.assertTrue(viewModel.viewData.value is ViewData.Loading)
        viewModel.start("MUG")
        Assert.assertTrue(viewModel.viewData.value is ViewData.Error)
    }

}