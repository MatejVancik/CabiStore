package com.cabify.cabistore

import com.cabify.cabistore.di.AppTestComponent
import com.cabify.cabistore.di.DaggerAppTestComponent
import com.cabify.cabistore.di.UserSessionTestComponent
import com.cabify.cabistore.server.MockProductServer
import com.cabify.store.product.di.ProductApiModule
import javax.inject.Inject

class CabiStoreTestApp : CabiStoreApp() {

    override val appComponent: AppTestComponent by lazy {
        DaggerAppTestComponent.factory()
            .create(this)
    }

    @Inject
    lateinit var userSessionComponentFactory: UserSessionTestComponent.Factory

    override fun startUserSession() {
        userSessionComponent = appComponent.userSessionTestComponentFactory.create(
            ProductApiModule(MockProductServer.baseUrl)
        )
    }

}