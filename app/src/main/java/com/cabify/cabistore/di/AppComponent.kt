package com.cabify.cabistore.di

import android.content.Context
import com.cabify.cabistore.di.session.UserSessionComponent
import com.cabify.store.core.android.di.CoreAndroidModule
import com.cabify.store.core.di.CoreModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.DispatchingAndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        CoreModule::class,
        CoreAndroidModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Context): AppComponent
    }

    val dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    val userSessionComponentFactory: UserSessionComponent.Factory

}