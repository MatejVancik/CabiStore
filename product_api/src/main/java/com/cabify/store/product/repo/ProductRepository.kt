package com.cabify.store.product.repo

import com.cabify.store.product.repo.data.ProductListDto
import io.reactivex.Single

interface ProductRepository {

    fun getProducts(): Single<ProductListDto>

}