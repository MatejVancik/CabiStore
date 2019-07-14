package com.cabify.store.cart.di

import com.cabify.store.cart.domain.*
import com.cabify.store.cart.repo.CartRepository
import com.cabify.store.cart.repo.CartRepositoryImpl
import com.cabify.store.cart.repo.data.mapper.CartMapper
import com.cabify.store.cart.repo.data.mapper.CartMapperImpl
import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.product.domain.GetAllProductsUseCase
import com.cabify.store.product.domain.GetProductUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CartApiModule {

    @Provides
    @Singleton
    fun provideAddItemToCartUseCase(
        cartRepository: CartRepository,
        schedulerProvider: SchedulerProvider
    ): AddItemToCartUseCase {
        return AddItemToCartUseCaseImpl(cartRepository, schedulerProvider)
    }

    @Provides
    @Singleton
    fun provideGetCartItemUseCaseImpl(
        cartRepository: CartRepository,
        getProductUseCase: GetProductUseCase,
        schedulerProvider: SchedulerProvider
    ): GetCartItemUseCase {
        return GetCartItemUseCaseImpl(cartRepository, getProductUseCase, schedulerProvider)
    }

    @Provides
    @Singleton
    fun provideDeleteCartItemUseCase(
        cartRepository: CartRepository,
        schedulerProvider: SchedulerProvider
    ): DeleteCartItemUseCase {
        return DeleteCartItemUseCaseImpl(cartRepository, schedulerProvider)
    }

    @Provides
    @Singleton
    fun provideGetCartUseCase(
        cartRepository: CartRepository,
        schedulerProvider: SchedulerProvider,
        getAllProductsUseCase: GetAllProductsUseCase
    ): GetCartUseCase {
        return GetCartUseCaseImpl(cartRepository, schedulerProvider, getAllProductsUseCase)
    }

    @Provides
    @Singleton
    fun provideObserveCartUseCase(
        cartRepository: CartRepository,
        getAllProductsUseCase: GetAllProductsUseCase,
        schedulerProvider: SchedulerProvider
    ): ObserveCartUseCase {
        return ObserveCartUseCaseImpl(cartRepository, getAllProductsUseCase, schedulerProvider)
    }

    @Provides
    @Singleton
    fun provideUpdateCartUseCase(
        cartRepository: CartRepository,
        schedulerProvider: SchedulerProvider
    ): UpdateCartItemUseCase {
        return UpdateCartItemUseCaseImpl(cartRepository, schedulerProvider)
    }

    @Provides
    @Singleton
    fun provideCartRepository(cartMapper: CartMapper, schedulerProvider: SchedulerProvider): CartRepository {
        return CartRepositoryImpl(cartMapper, schedulerProvider)
    }

    @Provides
    @Singleton
    fun provideCartMapper(): CartMapper {
        return CartMapperImpl()
    }

}