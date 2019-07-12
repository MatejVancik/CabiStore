package com.cabify.cabistore.di

import android.content.Context
import com.cabify.cabistore.CabiStoreApp
import com.cabify.store.core.android.di.CoreAndroidModule
import com.cabify.store.core.di.CoreModule
import com.cabify.store.product.di.ProductApiModule
import com.cabify.store.product.presentation.di.ProductComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        CoreModule::class,
        CoreAndroidModule::class,
        // Should be scoped to user session which would be above session features.
        // For simplicity it's scoped to application lifecycle.
        ProductApiModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Context): AppComponent
    }

    fun inject(cabiStoreApp: CabiStoreApp)

    val productComponentFactory: ProductComponent.Factory

}