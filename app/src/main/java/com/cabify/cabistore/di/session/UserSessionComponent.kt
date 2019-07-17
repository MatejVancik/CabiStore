package com.cabify.cabistore.di.session

import com.cabify.cabistore.di.ActivityBuilder
import com.cabify.store.cart.di.CartApiModule
import com.cabify.store.cart.presentation.di.CartComponent
import com.cabify.store.core.di.UserSessionScope
import com.cabify.store.product.di.ProductApiModule
import com.cabify.store.product.presentation.di.ProductComponent
import dagger.Subcomponent
import dagger.android.DispatchingAndroidInjector

@UserSessionScope
@Subcomponent(
    modules = [
        ActivityBuilder::class,
        ProductApiModule::class,
        CartApiModule::class
    ]
)
interface UserSessionComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(productApiModule: ProductApiModule): UserSessionComponent
    }

    val productComponentFactory: ProductComponent.Factory

    val cartComponentFactory: CartComponent.Factory

    val dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

}