package com.cabify.store.product.repo

import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.repo.data.mapper.ProductMapper
import io.reactivex.Single

class ProductRepositoryImpl(
    private val productRemoteApi: ProductRemoteApi,
    private val schedulerProvider: SchedulerProvider,
    private val productMapper: ProductMapper
) : ProductRepository {

    var cache: List<ProductData>? = null

    override fun getProducts(): Single<List<ProductData>> {
        return cache?.let { Single.just(it) } ?: productRemoteApi.getProductsList()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.computation())
            .map { it.products.map(productMapper::dtoToProductData) }
            .doOnSuccess { cache = it }
    }

}