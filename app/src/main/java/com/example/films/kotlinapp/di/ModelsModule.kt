package com.example.films.kotlinapp.di

import com.example.films.kotlinapp.data.wrappers.*
import com.example.films.kotlinapp.mvp.models.FilmModel
import com.example.films.kotlinapp.mvp.models.FilmModelProd
import com.example.films.kotlinapp.mvp.models.FilmsModel
import com.example.films.kotlinapp.mvp.models.FilmsModelProd
import com.example.films.kotlinapp.ui.list.generators.RecyclerViewListFiller
import com.example.films.kotlinapp.ui.list.generators.RecyclerViewMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val modelsModule = module {
    factory<FilmsModel> {
        FilmsModelProd(
            apiFilms = get(),
            mappersForSave = get(),
            filmsDao = get(),
            mapperRecyclerViewFiller = get(),
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

    factory<RecyclerViewListFiller> {
        RecyclerViewListFiller.Base(recyclerViewMapper = get())
    }

    factory<RecyclerViewMapper> {
        RecyclerViewMapper.Base()
    }

    factory<SelectedFilmMapper> {
        SelectedFilmMapper.Base(filmsDao = get())
    }

    factory<CoroutineDispatcher> {
        Dispatchers.Main
    }
}