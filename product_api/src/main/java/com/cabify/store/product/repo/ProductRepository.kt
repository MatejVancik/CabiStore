package com.cabify.store.product.repo

import com.cabify.store.product.domain.data.ProductData
import io.reactivex.Single

interface ProductRepository {

    fun getProducts(): Single<List<ProductData>>

}