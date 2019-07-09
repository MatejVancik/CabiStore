package com.cabify.store.product.domain

import com.cabify.store.product.domain.data.ProductData
import io.reactivex.Single

interface GetAllProductsUseCase {

    fun get(): Single<List<ProductData>>

}