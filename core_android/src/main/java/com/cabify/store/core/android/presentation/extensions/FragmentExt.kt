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

fun Fragment.toast(resource: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), resource, length).show()
}