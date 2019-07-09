package com.cabify.store.product.presentation.viewmodel

import com.cabify.store.core.android.presentation.base.BaseViewModel
import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import com.cabify.store.product.domain.GetAllProductsUseCase

class HomeViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val schedulerProvider: AndroidSchedulerProvider
): BaseViewModel() {



}