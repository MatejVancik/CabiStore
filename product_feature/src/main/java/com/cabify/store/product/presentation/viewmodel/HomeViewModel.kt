package com.cabify.store.product.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cabify.store.core.android.presentation.ViewData
import com.cabify.store.core.android.presentation.base.BaseViewModel
import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import com.cabify.store.core.android.utils.ResourceProvider
import com.cabify.store.product.R
import com.cabify.store.product.domain.GetAllProductsUseCase
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.presentation.data.HomeItemViewData
import com.cabify.store.product.presentation.data.HomeTitleItemViewData
import com.cabify.store.product.presentation.data.HomeViewData
import com.cabify.store.product.presentation.data.mapper.toHomeProductItemViewData

class HomeViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val schedulerProvider: AndroidSchedulerProvider,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    private val internalData = MutableLiveData<ViewData<HomeViewData>>().apply {
        value = ViewData.Loading()
    }
    val viewData: LiveData<ViewData<HomeViewData>>
        get() = internalData

    fun start() {
        getAllProductsUseCase.get()
            .observeOn(schedulerProvider.computation())
            .map(::mapToViewData)
            .observeOn(schedulerProvider.ui())
            .subscribe(
                { internalData.value = ViewData.Data(it) },
                { internalData.value = ViewData.Error(it) }
            )
            .bind()
    }

    private fun mapToViewData(items: List<ProductData>) = HomeViewData(buildSections(items))

    private fun buildSections(items: List<ProductData>): List<HomeItemViewData> {
        val title = resourceProvider.getString(R.string.our_products)
        val titleItem = HomeTitleItemViewData(title.hashCode().toLong(), title)
        val items = items.map {
            it.toHomeProductItemViewData(it.code.hashCode().toLong(), getProductResource(it.code))
        }

        return mutableListOf<HomeItemViewData>(titleItem).apply { addAll(items) }
    }

    private fun getProductResource(productId: String) = when (productId) {
        "TSHIRT" -> R.drawable.product_tshirt_small
        "MUG" -> R.drawable.product_mug_small
        "VOUCHER" -> R.drawable.product_vouche_small
        else -> R.drawable.product_default
    }

}