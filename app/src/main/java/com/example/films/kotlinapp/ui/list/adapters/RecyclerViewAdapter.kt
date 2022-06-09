package com.example.films.kotlinapp.ui.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.films.kotlinapp.mvp.models.entities.Film
import com.example.films.kotlinapp.ui.constants.UiConstants.TYPE_FILM
import com.example.films.kotlinapp.ui.constants.UiConstants.TYPE_GENRE
import com.example.films.kotlinapp.ui.constants.UiConstants.TYPE_HEADER
import com.example.films.kotlinapp.ui.list.ListItem
import com.example.films.kotlinapp.ui.list.SelectableData
import com.example.films.kotlinapp.ui.list.TitledImageData
import com.example.films.kotlinapp.ui.list.adapters.base.BaseSequenceAdapter
import com.example.films.kotlinapp.ui.list.adapters.base.ColumnMarginDecorator
import com.example.films.kotlinapp.ui.list.diff_utils.DiffUtilCallback
import com.example.films.kotlinapp.ui.list.diff_utils.DiffUtilsUpdater
import com.example.films.kotlinapp.ui.list.view_holders.BaseViewHolder
import com.example.films.kotlinapp.ui.list.view_holders.FilmViewHolder
import com.example.films.kotlinapp.ui.list.view_holders.GenreViewHolder
import com.example.films.kotlinapp.ui.list.view_holders.HeaderViewHolder

/**
 * RecyclerView Adapter для FilmsList view
 */
class RecyclerViewAdapter(layoutInflater: LayoutInflater) :
    BaseSequenceAdapter<ListItem, BaseViewHolder>(layoutInflater), DiffUtilsUpdater<ListItem> {

    private lateinit var filmViewHolderListener: FilmViewHolder.FilmViewHolderListener
    private lateinit var genreViewHolderListener: GenreViewHolder.GenreViewHolderListener
    override val typesSequence: IntArray
        get() = intArrayOf(
            TYPE_HEADER,
            TYPE_GENRE,
            TYPE_HEADER,
            TYPE_FILM
        )

    override fun getItemViewType(item: ListItem): Int {
        return when (item.data) {
            is String -> TYPE_HEADER
            is SelectableData -> TYPE_GENRE
            is TitledImageData -> TYPE_FILM
            else -> throwUnknownViewHolderTypeException()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_HEADER -> {
                (holder as HeaderViewHolder).bind(items[position])
            }
            TYPE_GENRE ->
                (holder as GenreViewHolder).bind(
                    items[position], genreViewHolderListener
                )
            TYPE_FILM -> (holder as FilmViewHolder).bind(
                items[position],
                filmViewHolderListener,
                ColumnMarginDecorator(items, position)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(layoutInflater, parent)
            TYPE_GENRE -> GenreViewHolder(layoutInflater, parent)
//            TYPE_FILMS_HEADER -> FilmsHeaderViewHolder(layoutInflater, parent)
            TYPE_FILM -> FilmViewHolder(layoutInflater, parent)
            else -> throwUnknownViewHolderTypeException()
        }
    }

    override fun updateWithDiffUtils(films: List<ListItem>) {
        val diff = DiffUtil.calculateDiff(DiffUtilCallback(items, films))
        updateItemsWithDiffUtil(films, diff)
    }

    fun setListeners(
        filmViewHolderListener: FilmViewHolder.FilmViewHolderListener,
        genreViewHolderListener: GenreViewHolder.GenreViewHolderListener
    ) {
        this.filmViewHolderListener = filmViewHolderListener
        this.genreViewHolderListener = genreViewHolderListener
    }
}