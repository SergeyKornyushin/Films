package com.example.films.kotlinapp.data.network

import com.example.films.kotlinapp.data.entities.network.FilmsDto
import retrofit2.Call
import retrofit2.http.GET

/**
 * ApiService
 */
interface ApiService {

    @GET("/sequeniatesttask/films.json")
    fun getFilms(): Call<FilmsDto>
}