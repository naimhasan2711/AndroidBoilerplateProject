package com.nakibul.android.boilerplateproject.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun formatPublishedAtDate(isoDate: String): String {
    val zonedDateTime = ZonedDateTime.parse(isoDate)
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return zonedDateTime.format(formatter)
}