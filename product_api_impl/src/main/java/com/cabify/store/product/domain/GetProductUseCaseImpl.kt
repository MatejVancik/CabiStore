package com.cabify.store.product.domain

import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.domain.data.ProductData
import io.reactivex.Single
import java.lang.IllegalArgumentException

class GetProductUseCaseImpl(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val schedulerProvider: SchedulerProvider
) : GetProductUseCase {

    override fun getProduct(productId: String): Single<ProductData> {
        return getAllProductsUseCase.get()
            .observeOn(schedulerProvider.computation())
            .map { it.find { it.code == productId } ?: throw IllegalArgumentException("Product not available") }
    }

}