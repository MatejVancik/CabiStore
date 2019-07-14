package com.cabify.store.cart.domain.data

/**
 * @param finalPriceCalculation calculates final price of all units of specific cart item
 */
enum class Discount(private val finalPriceCalculation: (CartItemData) -> Float) {

    /**
     * Discount rule - every second unit of this product is for free.
     * e.g. buy 5 vouchers and you'll pay only for 3.
     */
    OnePlusOne({ (it.count / 2 + it.count % 2) * it.pricePerItem }),

    /**
     * Discount rule - buy 3 or more of product and each will get 5% discount.
     * e.g. buy 5 t-shirts with original price of 20€ and each will be 19€.
     */
    DiscountOnThree({
        val originalPrice = it.count * it.pricePerItem
        if (it.count >= 3) originalPrice * 0.95f else originalPrice
    });

    /**
     * Calculates final price for given [CartItemData].
     */
    operator fun invoke(product: CartItemData): Float {
        return product.count * product.pricePerItem - finalPriceCalculation(product)
    }

}
