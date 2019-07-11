package com.cabify.store.product.presentation.di

import com.cabify.store.product.presentation.view.HomeFragment
import dagger.Subcomponent
import javax.inject.Provider

@Subcomponent(modules = [ProductModule::class])
interface ProductComponent {

    fun inject(fragment: HomeFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): ProductComponent
    }

}

interface ProductComponentProvider {

    fun productComponentBuilderProvider(): Provider<ProductComponent.Builder>

}