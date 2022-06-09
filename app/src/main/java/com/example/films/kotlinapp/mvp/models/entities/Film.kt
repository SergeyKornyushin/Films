package com.example.films.kotlinapp.mvp.models.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Entity для работы во view
 */
@Parcelize
data class Film(
    val filmId: Int,
    val image_url: String,
    val localized_name: String,
    val name: String,
    val year: String,
    val rating: String,
    val description: String,
    val genres: List<String> = emptyList()
) : Parcelable