package com.example.films.kotlinapp.ui.list.generators

import com.example.films.kotlinapp.data.entities.database.FilmDb
import com.example.films.kotlinapp.data.entities.database.GenreDb
import com.example.films.kotlinapp.ui.list.ListItem
import com.example.films.kotlinapp.ui.list.ListItemTypes
import com.example.films.kotlinapp.ui.list.SelectableData
import com.example.films.kotlinapp.ui.list.TitledImageData

/**
 * Интерфейс с мапперами для преобразования
 * entities database к entities для RecyclerView
 */
interface ComplexListMapper {

    fun mapFilmsDbToListItems(filmsFromDb: List<FilmDb>): List<ListItem>
    fun mapGenresDbToListItems(genresFromDb: List<GenreDb>): List<ListItem>
    fun mapGenresWithSelection(genresFromDb: List<GenreDb>, selectedGenre: String): List<ListItem>

    /**
     * Базовая реализация DomainRecyclerViewMapper
     */
    class Base : ComplexListMapper {
        override fun mapFilmsDbToListItems(filmsFromDb: List<FilmDb>): List<ListItem> =
            filmsFromDb.map { film ->
                ListItem(
                    type = ListItemTypes.ITEM,
                    id = film.filmId.toString(),
                    data = TitledImageData(
                        id = film.filmId,
                        title = film.localized_name,
                        imageUrl = film.image_url
                    )
                )
            }

        override fun mapGenresDbToListItems(genresFromDb: List<GenreDb>): List<ListItem> =
            genresFromDb.map { genre ->
                ListItem(
                    type = ListItemTypes.RADIO_BUTTON,
                    id = genre.genreId.toString(),
                    data = SelectableData(name = genre.genreName)
                )
            }

        override fun mapGenresWithSelection(
            genresFromDb: List<GenreDb>,
            selectedGenre: String
        ): List<ListItem> =
            genresFromDb.map { genre ->
                ListItem(
                    type = ListItemTypes.RADIO_BUTTON,
                    id = genre.genreId.toString(),
                    data = SelectableData(
                        id = genre.genreId,
                        name = genre.genreName,
                        value = genre.genreName == selectedGenre
                    )
                )
            }
    }
}