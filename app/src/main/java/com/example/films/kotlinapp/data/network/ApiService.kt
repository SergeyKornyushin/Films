package com.example.films.kotlinapp.data.network

import com.example.films.kotlinapp.data.network.responce.FilmsResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * ApiService
 */
interface ApiService {

    //Дергает список фильмов с сервера
    @GET("/sequeniatesttask/films.json")
    fun getFilms(): Call<FilmsResponse>
}