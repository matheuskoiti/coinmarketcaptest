package com.studiomk.domain.extensions

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String.formatDate(pattern: String = "dd/MM/yyyy"): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        parser.timeZone = TimeZone.getTimeZone("UTC")
        val date = parser.parse(this) ?: return this
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        formatter.format(date)
    } catch (e: Exception) {
        this
    }
}