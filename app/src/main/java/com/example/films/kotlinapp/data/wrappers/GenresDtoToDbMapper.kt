package com.example.films.kotlinapp.data.wrappers

import com.example.films.kotlinapp.data.entities.database.GenreDb
import com.example.films.kotlinapp.data.network.responce.FilmsResponse
import com.example.films.kotlinapp.mvp.models.base.ResponseMapper

/**
 * Маппер сетевого entity к entity GenreDb базы даннах
 */
class GenresDtoToDbMapper : ResponseMapper<FilmsResponse, List<GenreDb>> {
    override fun map(films: FilmsResponse): List<GenreDb> {
        val hashSet = HashSet<String>()
        films.filmsDto.forEach { film ->
            film.genres?.forEach { genre ->
                if (genre.isNotBlank()) hashSet.add(genre)
            }
        }
        val genresList: MutableList<GenreDb> = mutableListOf()
        hashSet.forEach { genre ->
            genresList.add(GenreDb(genre.replaceFirstChar { it.uppercase() }))
        }
        return genresList
    }
}