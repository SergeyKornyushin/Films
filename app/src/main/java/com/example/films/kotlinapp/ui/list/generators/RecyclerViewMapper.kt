package com.example.films.kotlinapp.ui.list.generators

import com.example.films.kotlinapp.data.entities.database.FilmDb
import com.example.films.kotlinapp.data.entities.database.GenreDb
import com.example.films.kotlinapp.mvp.models.entities.Film
import com.example.films.kotlinapp.mvp.models.entities.Genre
import com.example.films.kotlinapp.ui.list.ListItem
import com.example.films.kotlinapp.ui.list.ListItemTypes

/**
 * Интерфейс с мапперами для преобразования
 * entities database к entities для RecyclerView
 */
interface RecyclerViewMapper {

    fun mapFilmsToDomain(filmsFromDb: List<FilmDb>): List<ListItem>
    fun mapGenresToDomain(genresFromDb: List<GenreDb>): List<ListItem>
    fun mapGenresWithSelect(genresFromDb: List<GenreDb>, selectedGenre: Genre): List<ListItem>

    /**
     * Базовая реализация DomainRecyclerViewMapper
     */
    class Base : RecyclerViewMapper {
        override fun mapFilmsToDomain(filmsFromDb: List<FilmDb>): List<ListItem> =
            filmsFromDb.map { film ->
                ListItem(
                    type = ListItemTypes.LINK,
                    id = film.filmId.toString(),
                    data = Film(
                        filmId = film.filmId,
                        image_url = film.image_url,
                        name = film.name,
                        localized_name = film.localized_name,
                        year = film.year,
                        rating = film.rating,
                        description = film.description
                    )
                )
            }

        override fun mapGenresToDomain(genresFromDb: List<GenreDb>): List<ListItem> =
            genresFromDb.map { genre ->
                ListItem(
                    type = ListItemTypes.RADIO_BUTTON,
                    id = genre.genreName,
                    data = Genre(genre.genreName)
                )
            }

        override fun mapGenresWithSelect(
            genresFromDb: List<GenreDb>,
            selectedGenre: Genre
        ): List<ListItem> =
            genresFromDb.map { genre ->
                ListItem(
                    type = ListItemTypes.RADIO_BUTTON,
                    id = genre.genreName,
                    data = if (genre.genreName == selectedGenre.genreName) Genre(
                        genre.genreName,
                        true
                    ) else Genre(genre.genreName)
                )
            }
    }
}