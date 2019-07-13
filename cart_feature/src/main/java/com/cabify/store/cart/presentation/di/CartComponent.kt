package com.cabify.store.cart.presentation.di

import com.cabify.store.core.di.FeatureScope
import dagger.Subcomponent
import dagger.android.DispatchingAndroidInjector

@FeatureScope
@Subcomponent(
    modules = [
        CartFeatureModule::class,
        CartFragmentBuilder::class
    ]
)
interface CartComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CartComponent
    }

    fun androidInjector(): DispatchingAndroidInjector<Any>

}