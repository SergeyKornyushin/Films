package com.example.films.kotlinapp.ui.list

import android.text.InputType

/**
 * Данные обходимые для [ListItem] кнопки
 */
data class ButtonData(
    var name: String? = null
)

/**
 * Данные обходимые для [ListItem] заголовка
 */
data class HeaderData(
    var title: String? = null
)

/**
 * Данные для [ListItem] для заполнения значений
 */
data class InputData(
    var value: Any? = null,
    var name: String? = null,
    var hint: String? = null,
    var label: String? = null,
    var editable: Boolean = true,
    var inputType: Int = InputType.TYPE_CLASS_TEXT
)

/**
 * Данные обходимые для @listItem ссылки
 */
data class LinkData(
    var title: String?,
    var url: String?
)

/**
 * Данные [ListItem] для выделяемых полей (checkbox, radiobutton и т.д)
 */
data class SelectableData(
    var name: String?,
    var value: Boolean
)

/**
 * Данные [ListItem] для полей выбора
 */
class SelectData(
    var title: String? = null,
    var subTitle: String? = null,
    var value: Any? = null
)