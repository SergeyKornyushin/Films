package com.example.films.kotlinapp.di

import com.example.films.kotlinapp.mvp.presenters.FilmsPresenter
import org.koin.dsl.module

val presentersModule = module {
    factory {
        FilmsPresenter(
            filmsModel = get(),
            filmModel = get()
        )
    }
}