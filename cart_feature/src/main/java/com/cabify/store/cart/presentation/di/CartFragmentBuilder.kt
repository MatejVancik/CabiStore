package com.cabify.store.cart.presentation.di

import com.cabify.store.cart.presentation.view.CartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CartFragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributeCartFragment(): CartFragment

}