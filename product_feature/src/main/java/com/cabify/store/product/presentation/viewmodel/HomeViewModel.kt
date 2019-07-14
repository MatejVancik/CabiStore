package com.cabify.store.product.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.cabify.store.cart.domain.AddItemToCartUseCase
import com.cabify.store.core.android.presentation.base.BaseViewModel
import com.cabify.store.core.android.presentation.base.ViewDataHolder
import com.cabify.store.core.android.presentation.viewdata.ViewData
import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import com.cabify.store.product.domain.GetAllProductsUseCase
import com.cabify.store.product.presentation.data.HomeViewData
import com.cabify.store.product.presentation.data.mapper.HomeViewDataMapper

class HomeViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val schedulerProvider: AndroidSchedulerProvider,
    private val homeViewDataMapper: HomeViewDataMapper
) : BaseViewModel(), ViewDataHolder<HomeViewData> {

    override val viewData = MutableLiveData<ViewData<HomeViewData>>().apply {
        value = ViewData.Loading()
    }

    fun start() {
        getAllProductsUseCase.get()
            .observeOn(schedulerProvider.computation())
            .map(homeViewDataMapper::mapProductDataToViewData)
            .observeOn(schedulerProvider.ui())
            .subscribeToViewData()
            .bind()
    }

}