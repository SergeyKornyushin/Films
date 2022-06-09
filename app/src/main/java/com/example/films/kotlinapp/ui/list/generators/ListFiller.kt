package com.example.films.kotlinapp.ui.list.generators

import com.example.films.R
import com.example.films.kotlinapp.data.entities.database.FilmDb
import com.example.films.kotlinapp.data.entities.database.GenreDb
import com.example.films.kotlinapp.ui.list.ListItem
import com.example.films.kotlinapp.ui.list.ListItemTypes
import com.example.films.utils.ResourcesUtils

/**
 * Интерфейс с функцией наполнения конечного листа
 * для RecyclerView
 */
interface ListFiller {
    suspend fun createListForRecyclerView(
        genres: List<GenreDb>,
        films: List<FilmDb>,
        genre: String?
    ): List<ListItem>

    /**
     * Базовая реализация DomainListFiller
     */
    class Base(
        private val complexListMapper: ComplexListMapper
    ) : ListFiller {

        override suspend fun createListForRecyclerView(
            genres: List<GenreDb>,
            films: List<FilmDb>,
            genre: String?
        ): List<ListItem> {
            val rvList: MutableList<ListItem> = mutableListOf()

            rvList.add(getHeader(ResourcesUtils.getString(R.string.title_genres)))
            if (genre == null) {
                rvList.addAll(complexListMapper.mapGenresDbToListItems(genres))
            } else {
                rvList.addAll(complexListMapper.mapGenresWithSelection(genres, genre))
            }
            rvList.add(getHeader(ResourcesUtils.getString(R.string.title_films)))
            rvList.addAll(complexListMapper.mapFilmsDbToListItems(films))

            return rvList
        }

        private fun getHeader(title: String): ListItem {
            return ListItem(
                type = ListItemTypes.HEADER,
                data = title
            )
        }
    }
}