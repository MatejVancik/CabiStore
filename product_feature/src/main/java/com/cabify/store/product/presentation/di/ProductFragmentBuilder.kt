package com.cabify.store.product.presentation.di

import com.cabify.store.product.presentation.view.HomeFragment
import com.cabify.store.product.presentation.view.detail.ProductDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProductFragmentBuilder {

    @ContributesAndroidInjector
    abstract fun contributesHomeFragmentInjector(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributesProductDetailFragmentInjector(): ProductDetailFragment

}