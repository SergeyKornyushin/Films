package com.example.films.kotlinapp.data.network

import com.google.gson.JsonSyntaxException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Object для получения сообщений сетевых ошибок
 * с любого места в application
 */
object NetworkErrors {

    /**
     * Получить текст ошибки
     *
     * @param throwable exception для получения текста ошибки
     * @return текст ошибки
     */
    fun translateThrowable(throwable: Throwable): String {
        return getErrorMessage(throwable)
    }

    fun defaultError() = NetworkErrorMessage.SERVER_ERROR.getMessage()

    private fun getErrorMessage(throwable: Throwable): String {

        val networkErrorMessage = when (throwable) {
            is UnknownHostException,
            is ConnectException ->
                NetworkErrorMessage.NO_INTERNET_CONNECTION
            is SocketTimeoutException ->
                NetworkErrorMessage.SLOW_INTERNET_CONNECTION
            is SocketException ->
                NetworkErrorMessage.UNKNOWN
            is IOException,
            is JsonSyntaxException,
            is IllegalStateException ->
                NetworkErrorMessage.DATA_PARSING
            else ->
                NetworkErrorMessage.UNKNOWN
        }

        return networkErrorMessage.getMessage()
    }
}