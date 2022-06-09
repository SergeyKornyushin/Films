package com.example.films.kotlinapp.ui.list

/**
 * Универсальный объект отображения
 */
data class ListItem(
    var type: ListItemTypes? = null,
    var id: String? = null,
    var data: Any,
    var settings: Settings? = null,
    var errors: Errors? = null
)

/**
 * Ошибка объекта отображения
 */
data class Errors(var message: String)

/**
 * Настройки объекта отображения
 */
data class Settings(var column: Int)