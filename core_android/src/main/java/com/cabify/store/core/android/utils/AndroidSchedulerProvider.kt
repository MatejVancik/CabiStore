package com.cabify.store.core.android.utils

import com.cabify.store.core.utils.SchedulerProvider
import io.reactivex.Scheduler

interface AndroidSchedulerProvider: SchedulerProvider {

    fun ui(): Scheduler

}