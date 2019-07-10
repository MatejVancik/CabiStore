package com.cabify.store.core.extensions

import java.text.DecimalFormat

private val currencyFormat = DecimalFormat("#.00€")

fun Float.toPrice() = currencyFormat.format(this)