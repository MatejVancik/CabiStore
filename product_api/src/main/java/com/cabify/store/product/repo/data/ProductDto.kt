package com.cabify.store.product.repo.data

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductListDto(
    @JsonProperty("products") val products: List<ProductDto>
)

data class ProductDto(
    @JsonProperty("code") val code: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("price") val price: Float
)