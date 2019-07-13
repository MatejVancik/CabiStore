package com.cabify.store.cart.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cabify.store.cart.domain.GetCartUseCase
import com.cabify.store.cart.presentation.data.mapper.CartViewDataMapper
import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import java.lang.IllegalArgumentException

class CartViewModelFactory(
    private val getCartUseCase: GetCartUseCase,
    private val schedulerProvider: AndroidSchedulerProvider,
    private val cartViewDataMapper: CartViewDataMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(CartViewModel::class.java) -> CartViewModel(
                getCartUseCase,
                schedulerProvider,
                cartViewDataMapper
            )
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T


}