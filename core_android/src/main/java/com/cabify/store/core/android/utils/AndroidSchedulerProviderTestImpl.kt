package com.cabify.store.core.android.utils

import io.reactivex.schedulers.Schedulers

class AndroidSchedulerProviderTestImpl : AndroidSchedulerProvider {

    override fun io() = Schedulers.trampoline()

    override fun computation() = Schedulers.trampoline()

    override fun ui() = Schedulers.trampoline()

}