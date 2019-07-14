package com.cabify.store.core.android.presentation.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> Fragment.obtainViewModel(
    factory: ViewModelProvider.Factory
): T {
    return ViewModelProviders.of(this, factory).get(T::class.java)
}

fun <T : ViewModel> Fragment.obtainViewModel(
    factory: ViewModelProvider.Factory,
    key: String,
    viewModelClass: Class<T>
): T {
    return ViewModelProviders.of(this, factory).get(key, viewModelClass)
}

fun <T : ViewModel> Fragment.obtainActivityViewModel(
    factory: ViewModelProvider.Factory,
    viewModelClass: Class<T>
): T {
    return ViewModelProviders.of(activity!!, factory).get(viewModelClass)
}

fun Fragment.toast(resource: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), resource, length).show()
}