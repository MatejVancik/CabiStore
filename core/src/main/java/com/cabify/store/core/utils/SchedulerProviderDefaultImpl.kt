package com.cabify.store.core.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchedulerProviderDefaultImpl @Inject constructor(): SchedulerProvider {

    override fun io(): Scheduler = Schedulers.io()

    override fun computation(): Scheduler = Schedulers.computation()

}