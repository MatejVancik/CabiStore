package com.cabify.store.product.repo.data.mapper

import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.repo.data.ProductDto

interface ProductMapper {

    fun dtoToProductData(dto: ProductDto): ProductData

}