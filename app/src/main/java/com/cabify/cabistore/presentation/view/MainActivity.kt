package com.cabify.cabistore.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.cabify.cabistore.R
import com.cabify.store.product.presentation.di.ProductComponent
import com.cabify.store.product.presentation.di.ProductComponentProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ProductComponentProvider, HasAndroidInjector {

    @Inject
    lateinit var productComponentFactory: ProductComponent.Factory

    override val productComponent: ProductComponent by lazy {
        productComponentFactory.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragmentContainer)
        navigation.setupWithNavController(navController)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return productComponent.androidInjector()
    }

}
