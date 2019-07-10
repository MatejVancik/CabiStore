package com.cabify.store.core.android.utils

import android.content.Context
import androidx.core.content.ContextCompat

class ResourceProviderImpl(private val context: Context) : ResourceProvider {

    override fun getString(resId: Int): String = context.getString(resId)

    override fun getString(resId: Int, vararg obj: Any): String = context.getString(resId, *obj)

    override fun getQuantityString(resId: Int, quantity: Int, vararg obj: Any): String {
        return context.resources.getQuantityString(resId, quantity, *obj)
    }

    override fun getColor(resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    override fun getDimen(resId: Int): Float {
        return context.resources.getDimension(resId)
    }

}