package com.cabify.store.product

import com.cabify.store.product.domain.data.ProductData
import com.cabify.store.product.domain.data.ProductDiscount
import com.cabify.store.product.repo.data.ProductDto

val tshirtProductData = ProductData("TSHIRT", "T-Shirt", 20f, ProductDiscount.DiscountOnThree)

val mugProductDto = ProductDto("MUG", "Mug", 7f)
val mugProductData = ProductData("MUG", "Mug", 7f)