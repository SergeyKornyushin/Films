package com.example.films.kotlinapp.mvp.models

import com.example.films.kotlinapp.data.wrappers.SelectedFilmMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Реализация модели FilmModel
 */
class FilmModelProd(
    private val selectedFilmMapper: SelectedFilmMapper,
    coroutineDispatcher: CoroutineDispatcher
) : FilmModel {

    private val scope = CoroutineScope(SupervisorJob() + coroutineDispatcher)

    /**
     * Возвращает view entity Film
     */
    override fun getSelectedFilm(filmId: Int, callback: FilmModel.GetFilmCallback) {
        scope.launch {
            callback.onSuccess(
                selectedFilmMapper.getFilmById(filmId)
            )
        }
    }
}