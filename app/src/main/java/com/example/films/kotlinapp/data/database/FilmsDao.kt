package com.example.films.kotlinapp.data.database

import androidx.annotation.StringRes
import androidx.room.*
import com.example.films.kotlinapp.data.database.relations.FilmWithGenres
import com.example.films.kotlinapp.data.database.relations.FilmsGenresCrossRef
import com.example.films.kotlinapp.data.database.relations.GenreWithFilms
import com.example.films.kotlinapp.data.entities.database.FilmDb
import com.example.films.kotlinapp.data.entities.database.GenreDb

/**
 * Команды для работы с RoomDatabase
 */
@Dao
interface FilmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilm(filmDb: FilmDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genreDb: GenreDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilmsGenreCrossRef(crossRef: FilmsGenresCrossRef)

    @Transaction
    @Query("SELECT * FROM GenreDb WHERE genreName =:genre")
    suspend fun getGenreWithFilms(genre: String): GenreWithFilms

    @Transaction
    @Query("SELECT * FROM FilmDb WHERE filmId =:filmId")
    suspend fun getFilmWithGenres(filmId: Int): FilmWithGenres

    @Transaction
    @Query("SELECT * FROM GenreDb")
    suspend fun getAllGenres(): List<GenreDb>

    @Transaction
    @Query("SELECT * FROM FilmDb ORDER BY localized_name")
    suspend fun getAllFilms(): List<FilmDb>

    @Transaction
    @Query("SELECT * FROM FilmDb WHERE filmId =:filmId")
    suspend fun getFilmById(filmId: Int): FilmDb

    @Query("DELETE FROM FilmDb")
    suspend fun clearFilmsTable()

    @Query("DELETE FROM GenreDb")
    suspend fun clearGenresTable()

    @Query("DELETE FROM FilmsGenresCrossRef")
    suspend fun clearFilmsGenresCrossRefTable()

    @Transaction
    suspend fun clearAllTables() {
        clearFilmsTable()
        clearGenresTable()
        clearFilmsGenresCrossRefTable()
    }
}