package com.cabify.store.core.utils

import io.reactivex.schedulers.Schedulers

class SchedulerProviderTestImpl: SchedulerProvider {

    override fun io() = Schedulers.trampoline()

    override fun computation() = Schedulers.trampoline()

}