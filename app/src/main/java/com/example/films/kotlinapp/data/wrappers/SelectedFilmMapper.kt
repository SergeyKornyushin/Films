package com.example.films.kotlinapp.data.wrappers

import com.example.films.kotlinapp.data.database.FilmsDao
import com.example.films.kotlinapp.mvp.models.entities.Film

/**
 * Маппер для преобразования entity базы данных
 * в entity для работы во View
 */
interface SelectedFilmMapper {
    suspend fun getFilmById(filmId: Int): Film

    /**
     * Базовая реализация интерфейса SelectedFilmMapper
     */
    class Base(
        private val filmsDao: FilmsDao,
    ) : SelectedFilmMapper {

        override suspend fun getFilmById(filmId: Int): Film {
            val filmFromDb = filmsDao.getFilmById(filmId)
            val filmGenresFromDb = filmsDao.getFilmWithGenres(filmId)
            val film = Film(
                filmId = filmFromDb.filmId,
                image_url = filmFromDb.image_url,
                localized_name = filmFromDb.localized_name,
                name = filmFromDb.name,
                year = filmFromDb.year,
                rating = filmFromDb.rating,
                description = filmFromDb.description,
                genres = filmGenresFromDb.filmsDb.map { genreDb ->
                    genreDb.genreName
                }
            )
            return film
        }
    }
}