package com.cabify.store.core.android

import com.cabify.store.core.android.utils.AndroidSchedulerProvider
import com.cabify.store.core.utils.SchedulerProvider
import io.reactivex.schedulers.Schedulers

class AndroidSchedulerProviderTestImpl(
    testSchedulerProvider: SchedulerProvider
) : AndroidSchedulerProvider, SchedulerProvider by testSchedulerProvider {

    override fun ui() = Schedulers.trampoline()

}