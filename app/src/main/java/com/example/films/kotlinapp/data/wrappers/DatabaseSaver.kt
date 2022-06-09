package com.example.films.kotlinapp.data.wrappers

import com.example.films.kotlinapp.data.database.FilmsDao
import com.example.films.kotlinapp.data.entities.network.FilmsDto

/**
 * Набор мапперов для сохранения сетевого ответа в базу данных
 */
interface DatabaseSaver {
    suspend fun saveFilms(networkFilms: FilmsDto, filmsDao: FilmsDao)

    /**
     * Базовая реализация интерфейса MappersSet
     */
    data class Base(
        private val filmsDtoToDbMapper: FilmsDtoToDbMapper,
        private val genresDtoToDbMapper: GenresDtoToDbMapper,
        private val filmsGenresCrossRefMapper: FilmsGenresCrossRefMapper
    ) : DatabaseSaver {
        override suspend fun saveFilms(
            networkFilms: FilmsDto,
            filmsDao: FilmsDao
        ) {
            filmsDao.clearAllTables()
            filmsDtoToDbMapper.map(networkFilms).forEach { filmsDao.insertFilm(it) }
            genresDtoToDbMapper.map(networkFilms).forEach { filmsDao.insertGenre(it) }
            filmsGenresCrossRefMapper.map(networkFilms)
                .forEach { filmsDao.insertFilmsGenreCrossRef(it) }
        }
    }
}