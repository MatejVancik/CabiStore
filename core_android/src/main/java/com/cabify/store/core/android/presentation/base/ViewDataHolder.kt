package com.cabify.store.core.android.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cabify.store.core.android.presentation.viewdata.ViewData
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable

interface ViewDataHolder<T> {

    val viewData: LiveData<ViewData<T>>

    fun Single<T>.subscribeToViewData(): Disposable {
        return subscribe(
            { (viewData as MutableLiveData).value = ViewData.Data(it) },
            { (viewData as MutableLiveData).value = ViewData.Error(it) }
        )
    }

    fun Observable<T>.subscribeToViewData(): Disposable {
        return subscribe(
            { (viewData as MutableLiveData).value = ViewData.Data(it) },
            { (viewData as MutableLiveData).value = ViewData.Error(it) }
        )
    }

}