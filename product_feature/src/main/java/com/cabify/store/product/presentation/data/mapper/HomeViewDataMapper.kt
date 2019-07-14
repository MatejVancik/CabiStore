package com.cabify.store.product.presentation.data.mapper

import com.cabify.store.core.android.presentation.data.ProductResourceMapper
import com.cabify.store.core.android.utils.ResourceProvider
import com.cabify.store.core.extensions.toPrice
import com.cabify.store.product.R
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.presentation.data.HomeItemViewData
import com.cabify.store.product.presentation.data.HomeProductItemViewData
import com.cabify.store.product.presentation.data.HomeTitleItemViewData
import com.cabify.store.product.presentation.data.HomeViewData

class HomeViewDataMapper(
    private val resourceProvider: ResourceProvider
) {

    fun mapProductDataToViewData(items: List<ProductData>): HomeViewData {
        return HomeViewData(buildSections(items))
    }

    private fun buildSections(items: List<ProductData>): List<HomeItemViewData> {
        val title = resourceProvider.getString(R.string.our_products)
        val titleItem = HomeTitleItemViewData(title.hashCode().toLong(), title)
        val homeProducts = items.map {
            it.toHomeProductItemViewData(it.code.hashCode().toLong(), ProductResourceMapper.getProductResource(it.code))
        }

        return mutableListOf<HomeItemViewData>(titleItem).apply { addAll(homeProducts) }
    }

    private fun ProductData.toHomeProductItemViewData(identifier: Long, imageRes: Int) = HomeProductItemViewData(
        identifier, code, name, price.toPrice(), imageRes, discountType != null
    )

}
