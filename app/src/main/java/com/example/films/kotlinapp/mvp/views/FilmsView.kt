package com.example.films.kotlinapp.mvp.views

import com.example.films.kotlinapp.mvp.models.entities.Film
import com.example.films.kotlinapp.mvp.views.base.ContentLoadingView
import com.example.films.kotlinapp.ui.list.ListItem
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Интерфейс для FilmsPage view
 */
interface FilmsView : ContentLoadingView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFilms(films: List<ListItem>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showFilm(film: Film, genresWithYear: String)
}