package com.cabify.store.core.android.presentation.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflateView(layoutRes: Int, attachToGroup: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToGroup)
}