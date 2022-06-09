package com.example.films.kotlinapp.mvp.models

import com.example.films.kotlinapp.mvp.models.base.BaseCallback
import com.example.films.kotlinapp.mvp.models.entities.Genre
import com.example.films.kotlinapp.ui.list.ListItem

/**
 * Model для работы с FilmsPage view
 */
interface FilmsModel {

    fun getFilms(callback: GetFilmsCallback)
    fun getFilmsByGenre(genre: Genre, callback: GetFilmsCallback)

    /**
     * Callback для FilmsModel
     */
    interface GetFilmsCallback : BaseCallback<List<ListItem>>
}