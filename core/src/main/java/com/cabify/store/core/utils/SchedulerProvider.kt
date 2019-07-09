package com.cabify.store.core.utils

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler

    fun computation(): Scheduler

}