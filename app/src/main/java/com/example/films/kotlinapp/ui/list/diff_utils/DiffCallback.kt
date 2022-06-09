package com.example.films.kotlinapp.ui.list.diff_utils

import androidx.recyclerview.widget.DiffUtil
import com.example.films.kotlinapp.mvp.models.entities.Film
import com.example.films.kotlinapp.mvp.models.entities.FilmsHeader
import com.example.films.kotlinapp.mvp.models.entities.Genre
import com.example.films.kotlinapp.mvp.models.entities.GenresHeader
import com.example.films.kotlinapp.ui.list.ListItem

/**
 * Callback DiffUtils для RVAdapter
 */
class DiffCallback(private val oldItems: List<ListItem>, private val newItems: List<ListItem>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val isSameGenre =
            oldItems[oldItemPosition].data is Genre && newItems[newItemPosition].data is Genre

        val isSameFilm =
            oldItems[oldItemPosition].data is Film && newItems[newItemPosition].data is Film &&
                oldItems[oldItemPosition].id == newItems[newItemPosition].id

        val isSameGenresHeader =
            oldItems[oldItemPosition].data is GenresHeader &&
                newItems[newItemPosition].data is GenresHeader &&
                oldItems[oldItemPosition].id == newItems[newItemPosition].id

        val isSameFilmsHeader =
            oldItems[oldItemPosition].data is FilmsHeader &&
                newItems[newItemPosition].data is FilmsHeader &&
                oldItems[oldItemPosition].id == newItems[newItemPosition].id

        return isSameGenre || isSameFilm || isSameGenresHeader || isSameFilmsHeader
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition] == newItems[newItemPosition]
}