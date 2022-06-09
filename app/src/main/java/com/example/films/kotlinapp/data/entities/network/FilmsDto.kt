package com.example.films.kotlinapp.data.entities.network

import com.example.films.kotlinapp.data.entities.network.FilmDto
import com.google.gson.annotations.SerializedName

/**
 * Сущность с фильмами с ответа с сервера
 */
data class FilmsDto(
    @SerializedName("films")
    val filmsDto: MutableList<FilmDto>
)