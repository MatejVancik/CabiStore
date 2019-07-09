package com.cabify.store.product.repo

import com.cabify.store.product.repo.data.ProductListDto
import io.reactivex.Single
import retrofit2.http.GET

interface ProductRemoteApi {

    @GET("bins/4bwec")
    fun getProductsList(): Single<ProductListDto>

}