package com.cabify.store.product.presentation.viewmodel.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cabify.store.cart.domain.AddItemToCartUseCase
import com.cabify.store.core.android.presentation.viewdata.ViewData
import com.cabify.store.core.android.presentation.base.BaseViewModel
import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import com.cabify.store.product.domain.GetProductUseCase
import com.cabify.store.product.presentation.data.ProductDetailViewData
import com.cabify.store.product.presentation.data.mapper.detail.ProductDetailViewDataMapper

class DetailViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val detailViewDataMapper: ProductDetailViewDataMapper,
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val schedulerProvider: AndroidSchedulerProvider
) : BaseViewModel() {

    private val internalData = MutableLiveData<ViewData<ProductDetailViewData>>().apply {
        value = ViewData.Loading()
    }
    val viewData: LiveData<ViewData<ProductDetailViewData>>
        get() = internalData

    private lateinit var productId: String

    fun start(productId: String) {
        this.productId = productId

        getProductUseCase.getProduct(productId)
            .observeOn(schedulerProvider.computation())
            .map(detailViewDataMapper::productToDetailViewData)
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { internalData.value = ViewData.Data(it) },
                { internalData.value = ViewData.Error(it) }
            )
            .bind()
    }

    fun addToBasket(count: Int) {
        addItemToCartUseCase.add(productId, count)
            .observeOn(schedulerProvider.ui())
            .subscribe(
                {  },
                {  }
            )
            .bind()
    }

}