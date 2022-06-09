package com.example.films.kotlinapp.mvp.models.base

/**
 * Callback для обработки успеха
 */
interface SuccessCallback<Type> {
    /**
     * Срабатывает при успехе
     *
     * @param data полученные данные
     */
    fun onSuccess(data: Type)
}
