package com.example.films.kotlinapp.data.wrappers

import com.example.films.R
import com.example.films.kotlinapp.data.entities.database.FilmDb
import com.example.films.kotlinapp.data.network.responce.FilmsResponse
import com.example.films.kotlinapp.mvp.models.base.ResponseMapper
import com.example.films.kotlinapp.ui.extentions.avoidNullToString
import com.example.films.utils.ResourcesUtils

/**
 * Маппер сетевого entity к entity FilmDb базы даннах
 */
class FilmsDtoToDbMapper : ResponseMapper<FilmsResponse, List<FilmDb>> {
    override fun map(films: FilmsResponse): List<FilmDb> =
        films.filmsDto.map { film ->
            FilmDb(
                filmId = film.id ?: 0,
                image_url = film.image_url ?: "",
                localized_name = film.localized_name
                    .avoidNullToString(ResourcesUtils.getString(R.string.unknown)),
                name = film.name.avoidNullToString(ResourcesUtils.getString(R.string.unknown)),
                year = film.year.avoidNullToString(ResourcesUtils.getString(R.string.unknown)),
                rating = film.rating.avoidNullToString(ResourcesUtils.getString(R.string.unknown)),
                description = film.description
                    .avoidNullToString(ResourcesUtils.getString(R.string.unknown))
            )
        }
}