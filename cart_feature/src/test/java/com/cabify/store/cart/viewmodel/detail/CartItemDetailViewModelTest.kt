package com.cabify.store.cart.viewmodel.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cabify.store.cart.domain.DeleteCartItemUseCase
import com.cabify.store.cart.domain.GetCartItemUseCase
import com.cabify.store.cart.domain.UpdateCartItemUseCase
import com.cabify.store.cart.domain.data.CartItemData
import com.cabify.store.cart.presentation.data.CartItemDetailViewData
import com.cabify.store.cart.presentation.data.DeleteFailed
import com.cabify.store.cart.presentation.data.DismissDetail
import com.cabify.store.cart.presentation.data.UpdateFailed
import com.cabify.store.cart.presentation.data.mapper.CartItemDetailViewDataMapper
import com.cabify.store.cart.presentation.viewmodel.detail.CartItemDetailViewModel
import com.cabify.store.core.android.presentation.viewdata.ViewData
import com.cabify.store.core.android.utils.AndroidSchedulerProviderTestImpl
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CartItemDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getCartItemUseCase: GetCartItemUseCase

    @Mock
    lateinit var updateCartItemUseCase: UpdateCartItemUseCase

    @Mock
    lateinit var deleteCartItemUseCase: DeleteCartItemUseCase

    @Mock
    lateinit var cartItemDetailViewDataMapper: CartItemDetailViewDataMapper

    private val schedulerProvider = AndroidSchedulerProviderTestImpl()

    private lateinit var viewModel: CartItemDetailViewModel

    private val resultViewData = CartItemDetailViewData("MUG", 0, 1, "", null, "")

    @Before
    fun setup() {
        viewModel = CartItemDetailViewModel(
            getCartItemUseCase,
            updateCartItemUseCase,
            deleteCartItemUseCase,
            schedulerProvider,
            cartItemDetailViewDataMapper
        )
    }

    @Test
    fun `load view data successfully`() {
        startViewModelSuccessfully()

        Assert.assertEquals(resultViewData, (viewModel.viewData.value as ViewData.Data).content)
    }

    @Test
    fun `load view data error`() {
        whenever(getCartItemUseCase.get(any())).thenReturn(Single.error(Error()))
        viewModel.start("MUG")

        Assert.assertTrue(viewModel.viewData.value is ViewData.Error)
    }

    @Test
    fun `set number of items`() {
        whenever(updateCartItemUseCase.update(any(), any())).thenReturn(Completable.complete())
        startViewModelSuccessfully()

        viewModel.setNumberOfItems(1)
        verify(getCartItemUseCase, times(2)).get("MUG")
    }

    @Test
    fun `set number of items with error`() {
        whenever(updateCartItemUseCase.update(any(), any())).thenReturn(Completable.error(Error()))
        startViewModelSuccessfully()

        viewModel.setNumberOfItems(1)
        Assert.assertTrue(viewModel.events.value!!.peekContent() == UpdateFailed)
    }

    @Test
    fun `delete item successfully`() {
        whenever(deleteCartItemUseCase.delete(any())).thenReturn(Completable.complete())
        startViewModelSuccessfully()

        viewModel.deleteCartItem()
        Assert.assertTrue(viewModel.events.value!!.peekContent() == DismissDetail)
    }

    @Test
    fun `delete item error`() {
        whenever(deleteCartItemUseCase.delete(any())).thenReturn(Completable.error(Error()))
        startViewModelSuccessfully()

        viewModel.deleteCartItem()
        Assert.assertTrue(viewModel.events.value!!.peekContent() == DeleteFailed)
    }

    private fun startViewModelSuccessfully() {
        whenever(getCartItemUseCase.get(any())).thenReturn(Single.just(CartItemData("MUG")))
        whenever(cartItemDetailViewDataMapper.cartItemToDetailViewData((any()))).thenReturn(resultViewData)

        viewModel.start("MUG")
    }

}