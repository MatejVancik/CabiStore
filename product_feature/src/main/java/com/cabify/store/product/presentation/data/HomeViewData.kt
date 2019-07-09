package com.cabify.store.product.presentation.data

class HomeViewData {

}

data class HomeTitleItem(
    val title: String
)

data class HomeProductItemViewData(
    val productId: String,
    val title: String,
    val price: String,
    val imageLink: String
)