package com.cabify.store.cart.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.cabify.store.cart.domain.ObserveCartUseCase
import com.cabify.store.cart.presentation.data.CartViewData
import com.cabify.store.cart.presentation.data.mapper.CartViewDataMapper
import com.cabify.store.core.android.presentation.base.BaseViewModel
import com.cabify.store.core.android.presentation.base.ViewDataHolder
import com.cabify.store.core.android.presentation.viewdata.ViewData
import com.cabify.store.core.android.utils.AndroidSchedulerProvider

class CartViewModel(
    private val observeCartUseCase: ObserveCartUseCase,
    private val schedulerProvider: AndroidSchedulerProvider,
    private val cartViewDataMapper: CartViewDataMapper
) : BaseViewModel(), ViewDataHolder<CartViewData> {

    override val viewData = MutableLiveData<ViewData<CartViewData>>().apply {
        value = ViewData.Loading()
    }

    fun start() {
        observeCartUseCase.observe()
            .observeOn(schedulerProvider.computation())
            .map(cartViewDataMapper::mapCartToViewData)
            .observeOn(schedulerProvider.ui())
            .subscribeToViewData()
            .bind()
    }

}