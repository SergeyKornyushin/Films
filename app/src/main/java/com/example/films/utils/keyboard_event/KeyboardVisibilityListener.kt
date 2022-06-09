package com.example.films.utils.keyboard_event

/**
 * Слушатель на видимость клавиатуры
 */
interface KeyboardVisibilityListener {

    /**
     * Срабатывает при показе клавиатуры
     */
    fun onKeyboardShow() {
    }

    /**
     * Срабатывает при скрытии клавиатуры
     */
    fun onKeyboardHide() {
    }
}