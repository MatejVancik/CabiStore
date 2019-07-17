package com.cabify.cabistore.di

import android.content.Context
import com.cabify.store.core.android.di.CoreAndroidModule
import com.cabify.store.core.di.CoreModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        CoreModule::class,
        CoreAndroidModule::class
    ]
)
interface AppTestComponent: AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Context): AppTestComponent
    }

    val userSessionTestComponentFactory: UserSessionTestComponent.Factory

}