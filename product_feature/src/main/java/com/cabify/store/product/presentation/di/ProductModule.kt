package com.cabify.store.product.presentation.di

import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import com.cabify.store.core.android.utils.ResourceProvider
import com.cabify.store.core.di.FeatureScope
import com.cabify.store.product.domain.GetAllProductsUseCase
import com.cabify.store.product.presentation.viewmodel.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ProductModule {

    @Provides
    @FeatureScope
    fun provideHomeViewModelFactory(
        getAllProductsUseCase: GetAllProductsUseCase,
        schedulerProvider: AndroidSchedulerProvider,
        resourceProvider: ResourceProvider
    ): HomeViewModelFactory {
        return HomeViewModelFactory(getAllProductsUseCase, schedulerProvider, resourceProvider)
    }

}