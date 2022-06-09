package com.example.films.kotlinapp.data.database.relations

import androidx.room.Entity
import com.example.films.kotlinapp.data.constants.DataBaseConstants.FILM_ID
import com.example.films.kotlinapp.data.constants.DataBaseConstants.GENRE_NAME

/**
 * Таблица many-to-many relationship
 * между GenreDb & FilmDb
*/
@Entity(primaryKeys = [FILM_ID, GENRE_NAME])
data class FilmsGenresCrossRef(
    val filmId: Int,
    val genreName: String
)