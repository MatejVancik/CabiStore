package com.cabify.store.core.android.utils

import com.cabify.store.core.utils.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class AndroidSchedulerProviderImpl(
    schedulerProvider: SchedulerProvider
): AndroidSchedulerProvider, SchedulerProvider by schedulerProvider {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

}