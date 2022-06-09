package com.example.films.kotlinapp.ui.list.generators

import com.example.films.R
import com.example.films.kotlinapp.data.entities.database.FilmDb
import com.example.films.kotlinapp.data.entities.database.GenreDb
import com.example.films.kotlinapp.mvp.models.entities.FilmsHeader
import com.example.films.kotlinapp.mvp.models.entities.Genre
import com.example.films.kotlinapp.mvp.models.entities.GenresHeader
import com.example.films.kotlinapp.ui.list.ListItem
import com.example.films.kotlinapp.ui.list.ListItemTypes
import com.example.films.utils.ResourcesUtils

/**
 * Интерфейс с функцией наполнения конечного листа
 * для RecyclerView
 */
interface RecyclerViewListFiller {
    suspend fun createListForRecyclerView(
        genres: List<GenreDb>,
        films: List<FilmDb>,
        genre: Genre?
    ): List<ListItem>

    /**
     * Базовая реализация DomainListFiller
     */
    class Base(
        private val recyclerViewMapper: RecyclerViewMapper
    ) : RecyclerViewListFiller {

        private val titles: List<ListItem> = listOf(
            ListItem(
                type = ListItemTypes.TEXT,
                id = "1",
                data = GenresHeader(
                    name = ResourcesUtils.getString(R.string.title_genres)
                )
            ),
            ListItem(
                type = ListItemTypes.TEXT,
                id = "2",
                data = FilmsHeader(
                    name = ResourcesUtils.getString(R.string.title_films)
                )
            )
        )

        override suspend fun createListForRecyclerView(
            genres: List<GenreDb>,
            films: List<FilmDb>,
            genre: Genre?
        ): List<ListItem> {
            val rvList: MutableList<ListItem> = mutableListOf()
            rvList.add(titles[GENRES_HEADER])
            if (genre == null) {
                rvList.addAll(recyclerViewMapper.mapGenresToDomain(genres))
            } else {
                rvList.addAll(recyclerViewMapper.mapGenresWithSelect(genres, genre))
            }
            rvList.add(titles[FILMS_HEADER])
            rvList.addAll(recyclerViewMapper.mapFilmsToDomain(films))
            return rvList
        }
    }

    private companion object {
        const val GENRES_HEADER = 0
        const val FILMS_HEADER = 1
    }
}