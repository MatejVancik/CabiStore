package com.cabify.store.cart.presentation.di

import com.cabify.store.cart.presentation.view.CartFragment
import com.cabify.store.cart.presentation.view.detail.CartItemDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CartFragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributeCartFragment(): CartFragment

    @ContributesAndroidInjector
    abstract fun contributeCartItemDetailFragment(): CartItemDetailFragment
}