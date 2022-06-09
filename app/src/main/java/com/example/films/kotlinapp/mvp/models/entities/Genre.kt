package com.example.films.kotlinapp.mvp.models.entities

/**
 * Entity для работы во view
 */
data class Genre(
    val genreName: String,
    var isSelected: Boolean = false
)