package com.cabify.store.core.di

import com.cabify.store.core.utils.SchedulerProvider
import com.cabify.store.core.utils.SchedulerProviderDefaultImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProviderDefaultImpl()
    }

}