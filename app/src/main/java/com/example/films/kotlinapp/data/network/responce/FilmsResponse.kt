package com.example.films.kotlinapp.data.network.responce

import com.example.films.kotlinapp.data.entities.network.FilmDto
import com.google.gson.annotations.SerializedName

/**
 * Сущность с фильмами с ответа с сервера
 */
data class FilmsResponse(
    @SerializedName("films")
    val filmsDto: MutableList<FilmDto>
)