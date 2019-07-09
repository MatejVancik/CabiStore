package com.cabify.store.core.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class SchedulerProviderDefaultImpl: SchedulerProvider {

    override fun io(): Scheduler = Schedulers.io()

    override fun computation(): Scheduler = Schedulers.computation()

}