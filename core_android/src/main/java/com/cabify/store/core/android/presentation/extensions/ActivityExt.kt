package com.cabify.store.core.android.presentation.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

fun <T : ViewModel> AppCompatActivity.obtainViewModel(
    factory: ViewModelProvider.Factory,
    viewModelClass: Class<T>
): T {
    return ViewModelProviders.of(this, factory).get(viewModelClass)
}