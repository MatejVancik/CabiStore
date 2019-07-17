package com.cabify.cabistore

import android.app.Application
import androidx.core.provider.FontRequest
import com.cabify.cabistore.di.AppComponent
import com.cabify.cabistore.di.DaggerAppComponent
import com.cabify.cabistore.di.session.UserSessionComponent
import com.cabify.store.product.di.ProductApiModule
import com.cabify.store.product.repo.ProductRemoteRepoConstants
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

open class CabiStoreApp : Application(), HasAndroidInjector {

    open val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }

    var userSessionComponent: UserSessionComponent? = null

    override fun onCreate() {
        super.onCreate()

        initCalligraphy()

        // There should be more advance mechanism to start user session in right time (in real app it would be probably
        // when we obtain session token).
        startUserSession()
    }

    private fun initCalligraphy() {
        ViewPump.init(
            ViewPump.builder().addInterceptor(
                CalligraphyInterceptor(
                    CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
                )
            ).build()
        )
    }

    open fun startUserSession() {
        userSessionComponent = appComponent.userSessionComponentFactory.create(
            ProductApiModule(ProductRemoteRepoConstants.productBaseUrl)
        )
    }

    fun finishUserSession() {
        userSessionComponent = null
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return AndroidInjector {
            appComponent.dispatchingAndroidInjector.maybeInject(it) ||
                userSessionComponent?.dispatchingAndroidInjector?.maybeInject(it) ?: false
        }
    }

}