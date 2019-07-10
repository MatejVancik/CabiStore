package com.cabify.store.product.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import com.cabify.store.core.android.utils.ResourceProvider
import com.cabify.store.product.domain.GetAllProductsUseCase

class HomeViewModelFactory(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val schedulerProvider: AndroidSchedulerProvider,
    private val resourceProvider: ResourceProvider
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(getAllProductsUseCase, schedulerProvider, resourceProvider)
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
    }

}