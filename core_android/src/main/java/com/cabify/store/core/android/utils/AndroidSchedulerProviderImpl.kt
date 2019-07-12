package com.cabify.store.core.android.utils

import com.cabify.store.core.utils.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AndroidSchedulerProviderImpl(
    schedulerProvider: SchedulerProvider
): AndroidSchedulerProvider, SchedulerProvider by schedulerProvider {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

}