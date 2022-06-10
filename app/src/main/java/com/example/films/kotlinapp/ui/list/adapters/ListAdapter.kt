package com.example.films.kotlinapp.ui.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.films.kotlinapp.ui.constants.UiConstants.TYPE_FILM
import com.example.films.kotlinapp.ui.constants.UiConstants.TYPE_GENRE
import com.example.films.kotlinapp.ui.constants.UiConstants.TYPE_HEADER
import com.example.films.kotlinapp.ui.list.ListItem
import com.example.films.kotlinapp.ui.list.ListItemTypes
import com.example.films.kotlinapp.ui.list.adapters.base.BaseAdapter
import com.example.films.kotlinapp.ui.list.adapters.base.ColumnMarginDecorator
import com.example.films.kotlinapp.ui.list.diff_utils.DiffUtilCallback
import com.example.films.kotlinapp.ui.list.diff_utils.DiffUtilsUpdater
import com.example.films.kotlinapp.ui.list.view_holders.base.BaseViewHolder
import com.example.films.kotlinapp.ui.list.view_holders.FilmViewHolder
import com.example.films.kotlinapp.ui.list.view_holders.GenreViewHolder
import com.example.films.kotlinapp.ui.list.view_holders.HeaderViewHolder

/**
 * RecyclerView Adapter для FilmsList view
 */
class ListAdapter(layoutInflater: LayoutInflater) :
    BaseAdapter<ListItem, BaseViewHolder>(layoutInflater), DiffUtilsUpdater<ListItem> {

    private lateinit var filmViewHolderListener: FilmViewHolder.FilmViewHolderListener
    private lateinit var genreViewHolderListener: GenreViewHolder.GenreViewHolderListener

    override fun getItemViewType(position: Int): Int {
        return when (items[position].type) {
            ListItemTypes.HEADER -> TYPE_HEADER
            ListItemTypes.RADIO_BUTTON -> TYPE_GENRE
            ListItemTypes.ITEM -> TYPE_FILM
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