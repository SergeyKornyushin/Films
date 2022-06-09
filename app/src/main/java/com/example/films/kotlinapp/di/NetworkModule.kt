package com.example.films.kotlinapp.di

import com.example.films.kotlinapp.data.constants.NetworkConstants.NETWORK_TIME_OUT_IN_MS
import com.example.films.kotlinapp.data.network.ApiService
import com.example.films.kotlinapp.data.network.NetworkManager
import org.koin.dsl.module

val networkModule = module {

    val apiUrl = "https://s3-eu-west-1.amazonaws.com/"

    single<ApiService> {
        NetworkManager(apiUrl, NETWORK_TIME_OUT_IN_MS).createApiService()
    }
}