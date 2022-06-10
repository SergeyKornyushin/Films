package com.example.films.kotlinapp.mvp.presenters

import com.example.films.kotlinapp.mvp.models.FilmModel
import com.example.films.kotlinapp.mvp.models.FilmsModel
import com.example.films.kotlinapp.mvp.models.entities.Film
import com.example.films.kotlinapp.mvp.presenters.base.BasePresenter
import com.example.films.kotlinapp.mvp.views.FilmsView
import com.example.films.kotlinapp.ui.list.ListItem

/**
 * Презентер для работы с FilmsPage view
 */
class FilmsPresenter(private val filmsModel: FilmsModel, private val filmModel: FilmModel) :
    BasePresenter<FilmsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getFilms()
    }

    private fun getFilms() {
        viewState.startContentLoading()
        filmsModel.getFilms(object : FilmsModel.GetFilmsCallback {
            override fun onSuccess(data: List<ListItem>) {
                viewState.endContentLoading()
                viewState.showFilms(data)
            }

            override fun onError(error: String) {
                viewState.endContentLoading()
                viewState.showContentLoadingError(error)
            }
        })
    }

    fun onGenreSelected(genre: String) {
        filmsModel.getFilmsByGenre(
            genre = genre,
            callback = object : FilmsModel.GetFilmsCallback {
                override fun onSuccess(data: List<ListItem>) {
                    viewState.showFilms(data)
                }

                override fun onError(error: String) {
                    viewState.showContentLoadingError(error)
                }
            }
        )
    }

    fun onFilmClicked(filmId: Int) {
        filmModel.getSelectedFilm(
            filmId = filmId,
            callback = object : FilmModel.GetFilmCallback {
                override fun onSuccess(data: Film) {
                    val stringBuilder = StringBuilder()
                    data.genres.forEach { genre ->
                        stringBuilder.append("${genre.lowercase()}, ")
                    }
                    stringBuilder.append(data.year)

                    viewState.showFilmInfo(data, stringBuilder.toString())
                }

                override fun onError(error: String) {
                    viewState.showContentLoadingError(error)
                }
            }
        )
    }

    fun onRepeatButtonClicked() {
        getFilms()
    }
}