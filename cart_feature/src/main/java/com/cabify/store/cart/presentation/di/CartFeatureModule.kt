package com.cabify.store.cart.presentation.di

import com.cabify.store.cart.domain.GetCartUseCase
import com.cabify.store.cart.presentation.data.mapper.CartViewDataMapper
import com.cabify.store.cart.presentation.viewmodel.CartViewModelFactory
import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import com.cabify.store.core.di.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class CartFeatureModule {

    @Provides
    @FeatureScope
    fun provideCartViewDataMapper(): CartViewDataMapper {
        return CartViewDataMapper()
    }

    @Provides
    @FeatureScope
    fun provideCartViewModelFactory(
        getCartUseCase: GetCartUseCase,
        schedulerProvider: AndroidSchedulerProvider,
        cartViewDataMapper: CartViewDataMapper
    ): CartViewModelFactory {
        return CartViewModelFactory(getCartUseCase, schedulerProvider, cartViewDataMapper)
    }
}