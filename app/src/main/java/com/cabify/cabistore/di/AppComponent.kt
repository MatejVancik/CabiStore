package com.cabify.cabistore.di

import com.cabify.store.product.presentation.di.ProductComponentProvider
import dagger.Component

@Component(
    modules = [AppModule::class]
)
interface AppComponent: ProductComponentProvider {


}