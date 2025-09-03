package com.studiomk.domain.extensions

import java.text.NumberFormat
import java.util.Locale

fun Double.mapToCurrency(): String =
    NumberFormat.getCurrencyInstance(Locale.US).format(this)