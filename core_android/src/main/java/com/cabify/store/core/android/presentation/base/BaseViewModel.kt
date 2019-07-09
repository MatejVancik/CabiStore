package com.cabify.store.core.android.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel: ViewModel() {

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.dispose()
        compositeDisposable = null
    }

    /**
     * Binds this disposable to the ViewModel and disposes it with when ViewModel is cleared.
     */
    protected fun Disposable.bind() {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }

        compositeDisposable?.add(this)
    }

}