package com.cabify.store.product.presentation.data.mapper.detail

import com.cabify.store.core.android.utils.ResourceProvider
import com.cabify.store.core.extensions.toPrice
import com.cabify.store.product.R
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.domain.data.ProductDiscount
import com.cabify.store.product.presentation.data.ProductDetailViewData

class ProductDetailViewDataMapper(
    private val resourceProvider: ResourceProvider
) {

    fun productToDetailViewData(productData: ProductData): ProductDetailViewData {
        return ProductDetailViewData(
            productData.name,
            getProductResource(productData.code),
            productData.discountType?.getPromotionCopy(productData),
            productData.price.toPrice()
        )
    }

    private fun ProductDiscount.getPromotionCopy(productData: ProductData): String {
        return when (this) {
            ProductDiscount.OnePlusOne -> resourceProvider.getString(R.string.discount_one_plus_one, productData.name)
            ProductDiscount.DiscountOnThree -> {
                resourceProvider.getString(R.string.discount_three_for_less, productData.name, productData.price)
            }
        }
    }

    private fun getProductResource(productId: String) = when (productId) {
        "TSHIRT" -> R.drawable.product_tshirt_small
        "MUG" -> R.drawable.product_mug_small
        "VOUCHER" -> R.drawable.product_vouche_small
        else -> R.drawable.product_default
    }
}