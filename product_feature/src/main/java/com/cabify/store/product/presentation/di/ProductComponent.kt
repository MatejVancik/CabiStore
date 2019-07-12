package com.cabify.store.product.presentation.di

import com.cabify.store.core.di.FeatureScope
import dagger.Subcomponent
import dagger.android.DispatchingAndroidInjector

@FeatureScope
@Subcomponent(
    modules = [
        ProductModule::class,
        ProductFragmentBuilder::class
    ]
)
interface ProductComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ProductComponent
    }

    fun androidInjector(): DispatchingAndroidInjector<Any>

}