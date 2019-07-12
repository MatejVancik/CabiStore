package com.cabify.store.core.android.di

import android.content.Context
import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import com.cabify.store.core.android.utils.AndroidSchedulerProviderImpl
import com.cabify.store.core.android.utils.ResourceProvider
import com.cabify.store.core.android.utils.ResourceProviderImpl
import com.cabify.store.core.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreAndroidModule {

    @Provides
    @Singleton
    fun provideAndroidSchedulerProvider(schedulerProvider: SchedulerProvider): AndroidSchedulerProvider {
        return AndroidSchedulerProviderImpl(schedulerProvider)
    }

    @Provides
    @Singleton
    fun provideResourceProvider(context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }

}