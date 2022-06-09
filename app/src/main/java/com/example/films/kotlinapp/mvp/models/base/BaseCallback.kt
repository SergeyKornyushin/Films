package com.example.films.kotlinapp.mvp.models.base

/**
 * Интерфейс базового callback'а
 */
interface BaseCallback<Type> : ErrorCallback, SuccessCallback<Type> {
    override fun onSuccess(data: Type)

    override fun onError(error: String)
}