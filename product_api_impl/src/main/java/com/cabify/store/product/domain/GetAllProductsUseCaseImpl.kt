package com.cabify.store.product.domain

import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.repo.ProductRepository
import com.cabify.store.product.repo.data.mapper.ProductMapper
import io.reactivex.Single

class GetAllProductsUseCaseImpl(
    private val productRepository: ProductRepository,
    private val productMapper: ProductMapper,
    private val schedulerProvider: SchedulerProvider
): GetAllProductsUseCase {

    override fun get(): Single<List<ProductData>> {
        return productRepository.getProducts()
            .observeOn(schedulerProvider.computation())
            .map { it.products.map(productMapper::dtoToProductData) }
    }

}