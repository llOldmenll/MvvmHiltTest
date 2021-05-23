package com.exercise.domain.utils

import java.text.SimpleDateFormat
import java.util.Locale

// Default input date sample - 2001-07-04T12:08:56.235453-07:00
// Default output date sample - 4 Jul 2001 12:08:56
fun String.toFormattedDate(
    inputFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX",
    outputFormat: String = "d MMM yyyy HH:mm:ss",
    locale: Locale = Locale.getDefault()
): String {
    if (this.isBlank()) return this

    val parser = SimpleDateFormat(inputFormat, locale)
    val formatter = SimpleDateFormat(outputFormat, locale)
    return formatter.format(parser.parse(this))
}