package com.example.films.kotlinapp.mvp.views

import com.example.films.kotlinapp.mvp.models.entities.Film
import com.example.films.kotlinapp.mvp.views.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Интерфейс для FilmsPage view
 */
interface FilmView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFilm(film: Film, genresWithYear: String)
}