package com.cabify.store.core.android.presentation.viewdata

sealed class ViewData<T> {

    data class Loading<T>(val isLoading: Boolean = true) : ViewData<T>()

    data class Data<T>(val content: T) : ViewData<T>()

    data class Error<T>(val throwable: Throwable) : ViewData<T>()

}