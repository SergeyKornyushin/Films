package com.example.films.kotlinapp.di

import com.example.films.kotlinapp.data.wrappers.*
import com.example.films.kotlinapp.mvp.models.FilmModel
import com.example.films.kotlinapp.mvp.models.FilmModelProd
import com.example.films.kotlinapp.mvp.models.FilmsModel
import com.example.films.kotlinapp.mvp.models.FilmsModelProd
import com.example.films.kotlinapp.ui.list.generators.ComplexListMapper
import com.example.films.kotlinapp.ui.list.generators.ListFiller
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val modelsModule = module {
    factory<FilmsModel> {
        FilmsModelProd(
            apiFilms = get(),
            mappersForSave = get(),
            filmsDao = get(),
            mapperFiller = get(),
            coroutineDispatcher = get()
        )
    }

    factory<FilmModel> {
        FilmModelProd(
            selectedFilmMapper = get(),
            coroutineDispatcher = get()
        )
    }

    factory<DatabaseSaver> {
        DatabaseSaver.Base(
            FilmsDtoToDbMapper(),
            GenresDtoToDbMapper(),
            FilmsGenresCrossRefMapper()
        )
    }

    factory<ListFiller> {
        ListFiller.Base(complexListMapper = get())
    }

    factory<ComplexListMapper> {
        ComplexListMapper.Base()
    }

    factory<SelectedFilmMapper> {
        SelectedFilmMapper.Base(filmsDao = get())
    }

    factory<CoroutineDispatcher> {
        Dispatchers.Main
    }
}