package com.cabify.cabistore.di

import com.cabify.cabistore.presentation.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributeMainActivityInjector(): MainActivity

}