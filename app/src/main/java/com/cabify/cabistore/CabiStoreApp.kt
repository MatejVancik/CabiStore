package com.cabify.cabistore

import android.app.Application
import com.cabify.cabistore.di.AppComponent
import com.cabify.cabistore.di.AppComponentProvider
import com.cabify.cabistore.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.github.inflationx.calligraphy3.CalligraphyConfig
import javax.inject.Inject
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump


class CabiStoreApp : Application(), AppComponentProvider, HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

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

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

}