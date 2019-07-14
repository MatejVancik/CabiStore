package com.cabify.store.cart.presentation.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cabify.store.cart.domain.DeleteCartItemUseCase
import com.cabify.store.cart.domain.GetCartItemUseCase
import com.cabify.store.cart.domain.UpdateCartItemUseCase
import com.cabify.store.cart.presentation.data.*
import com.cabify.store.cart.presentation.data.mapper.CartItemDetailViewDataMapper
import com.cabify.store.core.android.presentation.viewdata.ViewData
import com.cabify.store.core.android.presentation.base.BaseViewModel
import com.cabify.store.core.android.presentation.event.Event
import com.cabify.store.core.android.utils.AndroidSchedulerProvider

class CartItemDetailViewModel(
    private val getCartItemUseCase: GetCartItemUseCase,
    private val updateCartItemUseCase: UpdateCartItemUseCase,
    private val deleteCartItemUseCase: DeleteCartItemUseCase,
    private val schedulerProvider: AndroidSchedulerProvider,
    private val cartItemDetailViewDataMapper: CartItemDetailViewDataMapper
) : BaseViewModel() {

    private val internalData = MutableLiveData<ViewData<CartItemDetailViewData>>().apply {
        value = ViewData.Loading()
    }
    val viewData: LiveData<ViewData<CartItemDetailViewData>>
        get() = internalData

    private val internalEvent = MutableLiveData<Event<CartItemDetailEvent>>()
    val events: LiveData<Event<CartItemDetailEvent>>
        get() = internalEvent

    private lateinit var productId: String

    fun start(productId: String) {
        this.productId = productId
        getCartItemUseCase.get(productId)
            .observeOn(schedulerProvider.computation())
            .map(cartItemDetailViewDataMapper::cartItemToDetailViewData)
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { internalData.value = ViewData.Data(it) },
                { internalData.value = ViewData.Error(it) }
            )
            .bind()
    }

    fun setNumberOfItems(count: Int) {
        updateCartItemUseCase.update(productId, count)
            .observeOn(schedulerProvider.ui())
            .subscribe(
                {
                    // Redraw UI with proper values
                    start(productId)
                },
                {
                    // Reset UI to latest state
                    internalData.value = internalData.value
                    internalEvent.value = Event(UpdateFailed)
                }
            )
            .bind()
    }

    fun deleteCartItem() {
        deleteCartItemUseCase.delete(productId)
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { internalEvent.value = Event(DismissDetail) },
                { internalEvent.value = Event(DeleteFailed) }
            )
            .bind()
    }

}