package com.example.films.kotlinapp.data.entities.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сущность Genre для RoomDatabase
 */
@Entity
data class GenreDb(
    @PrimaryKey(autoGenerate = true)
    val genreId: Int = 0,
    val genreName: String
)