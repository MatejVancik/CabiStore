package com.cabify.store.product.presentation.data.mapper

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

    private fun ProductData.toHomeProductItemViewData(identifier: Long, imageRes: Int) = HomeProductItemViewData(
        identifier, code, name, price.toPrice(), imageRes
    )

}
