package com.cabify.store.core.android.utils

interface ResourceProvider {

    fun getString(resId: Int): String

    fun getString(resId: Int, vararg obj: Any): String

    fun getQuantityString(resId: Int, quantity: Int, vararg obj: Any): String

    fun getColor(resId: Int): Int

    fun getDimen(resId: Int): Float


}