package com.example.films.kotlinapp.data.wrappers

import com.example.films.kotlinapp.data.database.relations.FilmsGenresCrossRef
import com.example.films.kotlinapp.data.network.responce.FilmsResponse
import com.example.films.kotlinapp.mvp.models.base.ResponseMapper

/**
 * Маппер сетевого entity к entity базы даннах для
 * FilmDb/GenreDb many-to-many relationship
 */
class FilmsGenresCrossRefMapper :
    ResponseMapper<FilmsResponse, List<FilmsGenresCrossRef>> {
    override fun map(films: FilmsResponse): List<FilmsGenresCrossRef> {
        val listFilmsGenres: MutableList<FilmsGenresCrossRef> = mutableListOf()
        films.filmsDto.forEach { film ->
            film.genres?.forEach { genre ->
                if (genre.isNotBlank()) {
                    listFilmsGenres.add(
                        FilmsGenresCrossRef(
                            filmId = film.id ?: 0,
                            genreName = genre.replaceFirstChar { it.uppercase() }
                        )
                    )
                }
            }
        }
        return listFilmsGenres
    }
}