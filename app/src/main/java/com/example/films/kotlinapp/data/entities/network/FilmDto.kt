package com.example.films.kotlinapp.data.entities.network

/**
 * Сущность Film с ответа с сервера
 */
data class FilmDto(
    val id: Int?,
    val genres: MutableList<String>?,
    val image_url: String?,
    val localized_name: String?,
    val name: String?,
    val year: Int?,
    val rating: Double?,
    val description: String?
)