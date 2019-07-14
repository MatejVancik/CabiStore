package com.cabify.cabistore.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cabify.cabistore.R
import com.cabify.store.cart.presentation.di.CartComponent
import com.cabify.store.cart.presentation.di.CartComponentProvider
import com.cabify.store.product.presentation.di.ProductComponent
import com.cabify.store.product.presentation.di.ProductComponentProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ProductComponentProvider, CartComponentProvider, HasAndroidInjector {

    @Inject
    lateinit var productComponentFactory: ProductComponent.Factory

    @Inject
    lateinit var cartComponentFactory: CartComponent.Factory

    override val productComponent: ProductComponent by lazy {
        productComponentFactory.create()
    }

    override val cartComponent: CartComponent by lazy {
        cartComponentFactory.create()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragmentContainer)
        navigation.setupWithNavController(navController)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return AndroidInjector {
            cartComponent.androidInjector().maybeInject(it) ||
                productComponent.androidInjector().maybeInject(it)
        }
    }

}
