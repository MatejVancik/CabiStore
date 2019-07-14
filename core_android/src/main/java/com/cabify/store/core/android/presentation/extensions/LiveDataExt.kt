package com.cabify.store.core.android.presentation.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.cabify.store.core.android.presentation.viewdata.ViewData
import com.cabify.store.core.android.presentation.viewdata.ViewDataObserver

fun <T> LiveData<ViewData<T>>.observe(
    owner: LifecycleOwner,
    observer: ViewDataObserver<T>
) {
    observe(owner, Observer {
        if (it !is ViewData.Loading) observer.onLoading(false)

        when (it) {
            is ViewData.Loading -> observer.onLoading(true)
            is ViewData.Data -> observer.onNewData(it.content)
            is ViewData.Error -> observer.onDataError(it.throwable)
        }
    })
}