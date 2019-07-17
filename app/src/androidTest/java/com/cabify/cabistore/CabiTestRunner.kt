package com.cabify.cabistore

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins

class CabiTestRunner : AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)

        RxJavaPlugins.setInitIoSchedulerHandler(Rx2Idler.create("IO Scheduler"))
        RxJavaPlugins.setInitComputationSchedulerHandler(Rx2Idler.create("Computation Scheduler"))
        RxJavaPlugins.setInitSingleSchedulerHandler(Rx2Idler.create("Single Scheduler"))
        RxJavaPlugins.setInitNewThreadSchedulerHandler(Rx2Idler.create("NewThread Scheduler"))
    }

    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, CabiStoreTestApp::class.java.name, context)
    }

}