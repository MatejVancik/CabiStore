package com.cabify.store.product.repo

import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.repo.data.ProductListDto
import io.reactivex.Single

class ProductRepositoryImpl(
    private val productRemoteApi: ProductRemoteApi,
    private val schedulerProvider: SchedulerProvider
): ProductRepository {

    override fun getProducts(): Single<ProductListDto> {
        return productRemoteApi.getProductsList()
            .subscribeOn(schedulerProvider.io())
    }

}