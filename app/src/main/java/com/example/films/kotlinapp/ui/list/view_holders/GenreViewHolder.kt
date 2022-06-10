package com.example.films.kotlinapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.films.R
import com.example.films.databinding.GenreItemBinding
import com.example.films.kotlinapp.ui.list.ListItem
import com.example.films.kotlinapp.ui.list.SelectableData
import com.example.films.kotlinapp.ui.list.view_holders.base.BaseViewHolder

/**
 * ViewHolder для Genre
 */
class GenreViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.genre_item) {
    private var binding: GenreItemBinding = GenreItemBinding.bind(itemView)
    private lateinit var listItem: ListItem
    private lateinit var listener: GenreViewHolderListener

    fun bind(listItem: ListItem, listener: GenreViewHolderListener) {
        this.listItem = listItem
        this.listener = listener

        showGenreName()
        showGenreStatus()
        setListener()
    }

    private fun showGenreName() {
        binding.genreText.text = (listItem.data as SelectableData).name
    }

    private fun showGenreStatus() {
        binding.genreText.isChecked = (listItem.data as SelectableData).value
    }

    private fun setListener() {
        itemView.setOnClickListener {
            listener.onGenreClick((listItem.data as SelectableData).name)
        }
    }

    /**
     * Слушатель нажатий GenreViewHolder
     */
    interface GenreViewHolderListener {
        fun onGenreClick(genre: String)
    }
}