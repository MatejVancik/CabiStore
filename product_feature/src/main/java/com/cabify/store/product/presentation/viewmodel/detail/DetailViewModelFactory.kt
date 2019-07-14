package com.cabify.store.product.presentation.viewmodel.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cabify.store.cart.domain.AddItemToCartUseCase
import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import com.cabify.store.product.domain.GetProductUseCase
import com.cabify.store.product.presentation.data.mapper.detail.ProductDetailViewDataMapper

class DetailViewModelFactory(
    private val getProductUseCase: GetProductUseCase,
    private val detailViewDataMapper: ProductDetailViewDataMapper,
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val schedulerProvider: AndroidSchedulerProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(
                getProductUseCase,
                detailViewDataMapper,
                addItemToCartUseCase,
                schedulerProvider
            )
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T

}