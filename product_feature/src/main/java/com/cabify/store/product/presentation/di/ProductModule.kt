package com.cabify.store.product.presentation.di

import com.cabify.store.cart.domain.AddItemToCartUseCase
import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import com.cabify.store.core.android.utils.ResourceProvider
import com.cabify.store.core.di.FeatureScope
import com.cabify.store.product.domain.GetAllProductsUseCase
import com.cabify.store.product.domain.GetProductUseCase
import com.cabify.store.product.presentation.data.mapper.HomeViewDataMapper
import com.cabify.store.product.presentation.data.mapper.detail.ProductDetailViewDataMapper
import com.cabify.store.product.presentation.viewmodel.HomeViewModelFactory
import com.cabify.store.product.presentation.viewmodel.detail.DetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ProductModule {

    @Provides
    @FeatureScope
    fun provideHomeViewModelFactory(
        getAllProductsUseCase: GetAllProductsUseCase,
        schedulerProvider: AndroidSchedulerProvider,
        homeViewDataMapper: HomeViewDataMapper
    ): HomeViewModelFactory {
        return HomeViewModelFactory(getAllProductsUseCase, schedulerProvider, homeViewDataMapper)
    }

    @Provides
    @FeatureScope
    fun provideHomeViewDataMapper(resourceProvider: ResourceProvider): HomeViewDataMapper {
        return HomeViewDataMapper(resourceProvider)
    }

    @Provides
    @FeatureScope
    fun provideDetailViewModelFactory(
        getProductUseCase: GetProductUseCase,
        detailViewDataMapper: ProductDetailViewDataMapper,
        addItemToCartUseCase: AddItemToCartUseCase,
        schedulerProvider: AndroidSchedulerProvider
    ): DetailViewModelFactory {
        return DetailViewModelFactory(getProductUseCase, detailViewDataMapper, addItemToCartUseCase, schedulerProvider)
    }

    @Provides
    @FeatureScope
    fun provideProductDetailViewDataMapper(resourceProvider: ResourceProvider): ProductDetailViewDataMapper {
        return ProductDetailViewDataMapper(resourceProvider)
    }

}