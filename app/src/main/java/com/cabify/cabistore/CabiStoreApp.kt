package com.cabify.cabistore

import android.app.Application
import com.cabify.cabistore.di.AppComponent
import com.cabify.cabistore.di.AppModule
import com.cabify.cabistore.di.DaggerAppComponent

class CabiStoreApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}