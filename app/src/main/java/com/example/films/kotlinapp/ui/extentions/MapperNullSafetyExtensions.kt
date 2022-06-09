package com.example.films.kotlinapp.ui.extentions

/**
 * Extension функции для фильтрации входящих "null"
 * и приведению к заданному строковому значению
 */
fun Int?.avoidNullToString(string: String): String {
    return this?.toString() ?: string
}

fun Double?.avoidNullToString(string: String): String {
    return if (this == null || this == -1.0) string
    else "%.1f".format(this).replace(",", ".")
}

fun String?.avoidNullToString(string: String): String {
    return if (this.isNullOrEmpty()) string else return this
}