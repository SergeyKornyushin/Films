package com.example.films.kotlinapp.mvp.models

import com.example.films.kotlinapp.mvp.models.base.BaseCallback
import com.example.films.kotlinapp.mvp.models.entities.Film

/**
 * Model для работы с FilmPage view
 */
interface FilmModel {

    fun getSelectedFilm(filmId: Int, callback: GetFilmCallback)

    /**
     * Callback для FilmModel
     */
    interface GetFilmCallback : BaseCallback<Film>
}