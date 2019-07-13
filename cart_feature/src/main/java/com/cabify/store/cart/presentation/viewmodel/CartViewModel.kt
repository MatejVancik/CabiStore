package com.cabify.store.cart.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cabify.store.cart.domain.GetCartUseCase
import com.cabify.store.cart.presentation.data.CartViewData
import com.cabify.store.cart.presentation.data.mapper.CartViewDataMapper
import com.cabify.store.core.android.presentation.ViewData
import com.cabify.store.core.android.presentation.base.BaseViewModel
import com.cabify.store.core.android.utils.AndroidSchedulerProvider

class CartViewModel(
    private val getCartUseCase: GetCartUseCase,
    private val schedulerProvider: AndroidSchedulerProvider,
    private val cartViewDataMapper: CartViewDataMapper
) : BaseViewModel() {

    private val internalData = MutableLiveData<ViewData<CartViewData>>().apply {
        value = ViewData.Loading()
    }
    val viewData: LiveData<ViewData<CartViewData>>
        get() = internalData

    fun start() {
        getCartUseCase.get()
            .observeOn(schedulerProvider.computation())
            .map(cartViewDataMapper::mapCartToViewData)
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { internalData.value = ViewData.Data(it) },
                { internalData.value = ViewData.Error(it) }
            )
            .bind()
    }

}