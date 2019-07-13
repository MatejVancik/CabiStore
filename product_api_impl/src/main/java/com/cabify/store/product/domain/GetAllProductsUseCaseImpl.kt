package com.cabify.store.product.domain

import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.domain.data.ProductDiscount
import com.cabify.store.product.repo.ProductRepository
import io.reactivex.Single

class GetAllProductsUseCaseImpl(
    private val productRepository: ProductRepository
): GetAllProductsUseCase {

    override fun get(): Single<List<ProductData>> {
        return productRepository.getProducts().map { it.map(::bindDiscounts) }
    }

    /**
     * This is the place where business logic decides which product discounts should be bound to specific products.
     */
    private fun bindDiscounts(product: ProductData): ProductData {
        return when (product.code) {
            "TSHIRT" -> product.copy(discountType = ProductDiscount.DiscountOnThree)
            "VOUCHER" -> product.copy(discountType = ProductDiscount.OnePlusOne)
            else -> product
        }
    }

}