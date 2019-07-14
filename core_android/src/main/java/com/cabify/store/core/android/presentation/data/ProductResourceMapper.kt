package com.cabify.store.core.android.presentation.data

import com.cabify.store.core.android.R

object ProductResourceMapper {

    // In real world, where image would be represented by URL this would come from DomainModel.
    @JvmStatic
    fun getProductResource(productId: String) = when (productId) {
        "TSHIRT" -> R.drawable.product_tshirt_small
        "MUG" -> R.drawable.product_mug_small
        "VOUCHER" -> R.drawable.product_vouche_small
        else -> R.drawable.product_default
    }

}