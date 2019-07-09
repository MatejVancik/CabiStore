package com.cabify.store.product.di

import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.repo.ProductRemoteApi
import com.cabify.store.product.repo.ProductRepository
import com.cabify.store.product.repo.ProductRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Named

@Module
class ProductApiModule {

    @Provides
    fun provideProductRepository(
        productRemoteApi: ProductRemoteApi,
        schedulerProvider: SchedulerProvider
    ): ProductRepository {
        return ProductRepositoryImpl(productRemoteApi, schedulerProvider)
    }

    @Provides
    fun provideProductRemoteApi(
        @Named("productApi") retrofit: Retrofit
    ): ProductRemoteApi {
        return retrofit.create(ProductRemoteApi::class.java)
    }

    @Named("productApi")
    @Provides
    fun provideProductApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.myjson.com")
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}