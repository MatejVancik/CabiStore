package com.cabify.store.cart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cabify.store.cart.domain.ObserveCartUseCase
import com.cabify.store.cart.presentation.data.mapper.CartViewDataMapper
import com.cabify.store.core.android.utils.AndroidSchedulerProvider

@Suppress("UNCHECKED_CAST")
class CartViewModelFactory(
    private val observeCartUseCase: ObserveCartUseCase,
    private val schedulerProvider: AndroidSchedulerProvider,
    private val cartViewDataMapper: CartViewDataMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(CartViewModel::class.java) -> CartViewModel(
                observeCartUseCase,
                schedulerProvider,
                cartViewDataMapper
            )
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
    }

}