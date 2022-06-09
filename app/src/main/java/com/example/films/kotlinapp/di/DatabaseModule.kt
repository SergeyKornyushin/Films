package com.example.films.kotlinapp.di

import androidx.room.Room
import com.example.films.kotlinapp.application.App.Companion.context
import com.example.films.kotlinapp.data.constants.DataBaseConstants.DATABASE_NAME
import com.example.films.kotlinapp.data.database.FilmsDao
import com.example.films.kotlinapp.data.database.FilmsDataBase
import org.koin.dsl.module

val databaseModule = module {
    single<FilmsDataBase> {
        Room.databaseBuilder(
            context,
            FilmsDataBase::class.java,
            DATABASE_NAME
        ).build()
    }

    single<FilmsDao> {
        val database = get<FilmsDataBase>()
        database.filmsDao()
    }
}
