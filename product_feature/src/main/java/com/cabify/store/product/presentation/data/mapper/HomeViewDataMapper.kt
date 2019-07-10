package com.cabify.store.product.presentation.data.mapper

import com.cabify.store.core.extensions.toPrice
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.presentation.data.HomeProductItemViewData

fun ProductData.toHomeProductItemViewData(identifier: Long, imageRes: Int) = HomeProductItemViewData(
    identifier, code, name, price.toPrice(), imageRes
)
