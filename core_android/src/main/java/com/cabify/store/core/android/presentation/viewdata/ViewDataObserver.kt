package com.cabify.store.core.android.presentation.viewdata

interface ViewDataObserver<T> {

    fun onLoading(isLoading: Boolean)

    fun onNewData(viewData: T)

    fun onDataError(error: Throwable)

}