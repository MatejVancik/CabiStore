package com.cabify.store.product.presentation.data

import com.cabify.store.core.android.presentation.adapter.Identifiable

data class HomeViewData(
    val recyclerData: List<HomeItemViewData>
)

sealed class HomeItemViewData: Identifiable

data class HomeTitleItemViewData(
    override val identifier: Long,
    val title: String
): HomeItemViewData()

data class HomeProductItemViewData(
    override val identifier: Long,
    val productId: String,
    val title: String,
    val price: String,
    val image: Int,
    val hasDiscount: Boolean = false
): HomeItemViewData()