package com.cabify.store.product.di

import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.domain.GetAllProductsUseCase
import com.cabify.store.product.domain.GetAllProductsUseCaseImpl
import com.cabify.store.product.domain.GetProductUseCase
import com.cabify.store.product.domain.GetProductUseCaseImpl
import com.cabify.store.product.repo.ProductRemoteApi
import com.cabify.store.product.repo.ProductRepository
import com.cabify.store.product.repo.ProductRepositoryImpl
import com.cabify.store.product.repo.data.mapper.ProductMapper
import com.cabify.store.product.repo.data.mapper.ProductMapperImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ProductApiModule {

    @Provides
    @Singleton
    fun provideGetAllProductsUseCase(productRepository: ProductRepository): GetAllProductsUseCase {
        return GetAllProductsUseCaseImpl(productRepository)
    }

    @Provides
    @Singleton
    fun provideGetProductUseCaseImpl(getAllProductsUseCase: GetAllProductsUseCase): GetProductUseCaseImpl {
        return GetProductUseCaseImpl(getAllProductsUseCase)
    }

    @Provides
    @Singleton
    fun provideGetProductUseCase(getAllProductsUseCase: GetAllProductsUseCase): GetProductUseCase {
        return GetProductUseCaseImpl(getAllProductsUseCase)
    }

    @Provides
    @Singleton
    fun provideProductMapper(): ProductMapper {
        return ProductMapperImpl()
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        productRemoteApi: ProductRemoteApi,
        schedulerProvider: SchedulerProvider,
        productMapper: ProductMapper
    ): ProductRepository {
        return ProductRepositoryImpl(productRemoteApi, schedulerProvider, productMapper)
    }

    @Provides
    @Singleton
    fun provideProductRemoteApi(@Named("productApi") retrofit: Retrofit): ProductRemoteApi {
        return retrofit.create(ProductRemoteApi::class.java)
    }

    /**
     * Retrofit created specifically for endpoints hosting products, assuming these endpoints will have same
     * configuration (e.g. model converters, certificate if we enable certificate pinning...). This way we can have
     * multiple retrofit instances made for specific hosts/BE configurations.
     */
    @Named("productApi")
    @Provides
    @Singleton
    fun provideProductApiRetrofit(@Named("productApi") httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.myjson.com")
            .client(httpClient)
            .addConverterFactory(JacksonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Named("productApi")
    @Provides
    @Singleton
    fun providesHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)
        }.build()
    }

}