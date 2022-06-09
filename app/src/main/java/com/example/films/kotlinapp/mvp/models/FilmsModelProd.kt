package com.example.films.kotlinapp.mvp.models

import com.example.films.kotlinapp.data.database.FilmsDao
import com.example.films.kotlinapp.data.network.responce.FilmsResponse
import com.example.films.kotlinapp.data.network.ApiService
import com.example.films.kotlinapp.data.network.NetworkCallback
import com.example.films.kotlinapp.data.wrappers.DatabaseSaver
import com.example.films.kotlinapp.mvp.models.entities.Genre
import com.example.films.kotlinapp.ui.list.ListItem
import com.example.films.kotlinapp.ui.list.generators.ListFiller
import kotlinx.coroutines.*

/**
 * Реализация модели FilmsModel
 */
class FilmsModelProd(
    private val apiFilms: ApiService,
    private val filmsDao: FilmsDao,
    private val mappersForSave: DatabaseSaver,
    private val mapperFiller: ListFiller,
    coroutineDispatcher: CoroutineDispatcher
) : FilmsModel {

    private val scope = CoroutineScope(SupervisorJob() + coroutineDispatcher)

    /**
     * Возвращает List<ListItem>
     * со всеми фильмами для RecyclerView
     */
    override fun getFilms(callback: FilmsModel.GetFilmsCallback) {
        apiFilms.getFilms().enqueue(object : NetworkCallback<FilmsResponse> {
            override fun onSuccess(response: FilmsResponse?) {
                if (response != null) {
                    scope.launch {
                        filmsDao.clearAllTables()
                        mappersForSave.saveFilms(response, filmsDao)
                        callback.onSuccess(
                            mapperFiller.createListForRecyclerView(
                                genres = filmsDao.getAllGenres(),
                                films = filmsDao.getAllFilms(),
                                null
                            )
                        )
                    }
                } else emptyList<ListItem>()
            }

            override fun onError(error: String) {
                callback.onError(error)
            }
        })
    }

    /**
     * Возвращает List<ListItem>
     * с фильмами конкретного жанра для RecyclerView
     */
    override fun getFilmsByGenre(genre: String, callback: FilmsModel.GetFilmsCallback) {
        scope.launch {
            callback.onSuccess(
                mapperFiller.createListForRecyclerView(
                    genres = filmsDao.getAllGenres(),
                    films = filmsDao.getGenreWithFilms(genre).filmsDb,
                    genre = genre
                )
            )
        }
    }
}