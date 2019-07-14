package com.cabify.store.cart.presentation.di

import com.cabify.store.cart.domain.*
import com.cabify.store.cart.presentation.data.mapper.CartItemDetailViewDataMapper
import com.cabify.store.cart.presentation.data.mapper.CartViewDataMapper
import com.cabify.store.cart.presentation.viewmodel.CartViewModelFactory
import com.cabify.store.cart.presentation.viewmodel.detail.CartItemDetailViewModelFactory
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
        observeCartUseCase: ObserveCartUseCase,
        schedulerProvider: AndroidSchedulerProvider,
        cartViewDataMapper: CartViewDataMapper
    ): CartViewModelFactory {
        return CartViewModelFactory(observeCartUseCase, schedulerProvider, cartViewDataMapper)
    }

    @Provides
    @FeatureScope
    fun provideCartItemDetailViewModelFactory(
        getCartItemUseCase: GetCartItemUseCase,
        updateCartItemUseCase: UpdateCartItemUseCase,
        deleteCartItemUseCase: DeleteCartItemUseCase,
        schedulerProvider: AndroidSchedulerProvider,
        cartItemDetailViewDataMapper: CartItemDetailViewDataMapper
    ): CartItemDetailViewModelFactory {
        return CartItemDetailViewModelFactory(
            getCartItemUseCase,
            updateCartItemUseCase,
            deleteCartItemUseCase,
            schedulerProvider,
            cartItemDetailViewDataMapper
        )
    }

    @Provides
    @FeatureScope
    fun provideCartItemDetailViewDataMapper(): CartItemDetailViewDataMapper {
        return CartItemDetailViewDataMapper()
    }
}