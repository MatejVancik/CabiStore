package com.cabify.store.product.domain

import com.cabify.store.product.domain.data.ProductData
import io.reactivex.Single

class GetProductUseCaseImpl(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : GetProductUseCase {

    override fun getProduct(productId: String): Single<ProductData> {
        return getAllProductsUseCase.get().map { it.find { it.code == productId } }
    }

}