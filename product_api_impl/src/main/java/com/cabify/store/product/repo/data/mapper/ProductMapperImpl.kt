package com.cabify.store.product.repo.data.mapper

import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.repo.data.ProductDto

class ProductMapperImpl : ProductMapper {

    override fun dtoToProductData(dto: ProductDto) = with(dto) { ProductData(code, name, price) }

}