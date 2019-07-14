package com.cabify.store.product.domain

import com.cabify.store.product.domain.data.ProductData
import io.reactivex.Single

interface GetProductUseCase {

    fun getProduct(productId: String): Single<ProductData>

}