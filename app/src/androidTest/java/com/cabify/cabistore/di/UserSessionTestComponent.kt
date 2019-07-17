package com.cabify.cabistore.di

import com.cabify.cabistore.CabiStoreTestApp
import com.cabify.cabistore.di.session.UserSessionComponent
import com.cabify.cabistore.test.CartTest
import com.cabify.store.cart.di.CartApiModule
import com.cabify.store.core.di.UserSessionScope
import com.cabify.store.product.di.ProductApiModule
import dagger.Subcomponent

@UserSessionScope
@Subcomponent(
    modules = [
        ActivityBuilder::class,
        ProductApiModule::class,
        CartApiModule::class
    ]
)
interface UserSessionTestComponent: UserSessionComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(productApiModule: ProductApiModule): UserSessionTestComponent
    }

    fun inject(cartTest: CartTest)

}