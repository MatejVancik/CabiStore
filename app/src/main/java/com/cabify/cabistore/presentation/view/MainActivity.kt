package com.cabify.cabistore.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.cabify.cabistore.CabiStoreApp
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
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.fragmentContainer)
        val appBarConfiguration = AppBarConfiguration.Builder(R.id.nav_home, R.id.nav_cart).build()
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
        navigation.setupWithNavController(navController)

        title = navController.graph.findNode(navigation.selectedItemId)?.label
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return AndroidInjector {
            productComponent.androidInjector().maybeInject(it) ||
                cartComponent.androidInjector().maybeInject(it)
        }
    }

}
