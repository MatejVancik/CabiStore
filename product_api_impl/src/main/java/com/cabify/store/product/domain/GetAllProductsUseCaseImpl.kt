package com.cabify.store.product.domain

import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.domain.data.ProductDiscount
import com.cabify.store.product.repo.ProductRepository
import io.reactivex.Single

class GetAllProductsUseCaseImpl(
    private val productRepository: ProductRepository,
    private val schedulerProvider: SchedulerProvider
): GetAllProductsUseCase {

    override fun get(): Single<List<ProductData>> {
        return productRepository.getProducts()
            .observeOn(schedulerProvider.computation())
            .map { it.map(::bindDiscounts) }
    }

    /**
     * This is the place where business logic decides which product discounts should be bound to specific products.
     * Ideally, this information should come from API and get here via ProductData. Hardcoding discounts within the app
     * is not scalable solution.
     */
    private fun bindDiscounts(product: ProductData): ProductData {
        return when (product.code) {
            "TSHIRT" -> product.copy(discountType = ProductDiscount.DiscountOnThree)
            "VOUCHER" -> product.copy(discountType = ProductDiscount.OnePlusOne)
            else -> product
        }
    }

}