package com.cabify.store.cart.presentation.viewmodel.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cabify.store.cart.domain.DeleteCartItemUseCase
import com.cabify.store.cart.domain.GetCartItemUseCase
import com.cabify.store.cart.domain.UpdateCartItemUseCase
import com.cabify.store.cart.presentation.data.mapper.CartItemDetailViewDataMapper
import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import java.lang.IllegalArgumentException

class CartItemDetailViewModelFactory(
    private val getCartItemUseCase: GetCartItemUseCase,
    private val updateCartItemUseCase: UpdateCartItemUseCase,
    private val deleteCartItemUseCase: DeleteCartItemUseCase,
    private val schedulerProvider: AndroidSchedulerProvider,
    private val cartItemDetailViewDataMapper: CartItemDetailViewDataMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(CartItemDetailViewModel::class.java) -> CartItemDetailViewModel(
                getCartItemUseCase,
                updateCartItemUseCase,
                deleteCartItemUseCase,
                schedulerProvider,
                cartItemDetailViewDataMapper
            )
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        } as T
    }

}