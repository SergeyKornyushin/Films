package com.example.films.kotlinapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.films.R
import com.example.films.databinding.GenreItemBinding
import com.example.films.kotlinapp.mvp.models.entities.Genre

/**
 * ViewHolder для Genre
 */
class GenreViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.genre_item) {
    private var binding: GenreItemBinding = GenreItemBinding.bind(itemView)
    private lateinit var genre: Genre
    private lateinit var listener: GenreViewHolderListener

    fun bind(genre: Genre, listener: GenreViewHolderListener) {
        this.genre = genre
        this.listener = listener

        showGenreName()
        setListener()
    }

    private fun showGenreName() {
        binding.genreText.text = genre.genreName
        binding.genreText.isChecked = genre.isSelected
    }

    private fun setListener() {
        itemView.setOnClickListener {
            listener.onGenreClick(genre)
        }
    }

    /**
     * Слушатель нажатий GenreViewHolder
     */
    interface GenreViewHolderListener {
        fun onGenreClick(genre: Genre)
    }
}