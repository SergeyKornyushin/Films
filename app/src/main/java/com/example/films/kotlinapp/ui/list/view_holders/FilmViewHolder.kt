package com.example.films.kotlinapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.films.R
import com.example.films.databinding.FilmItemBinding
import com.example.films.kotlinapp.mvp.models.entities.Film
import com.example.films.kotlinapp.ui.list.adapters.base.ColumnMarginDecorator
import com.example.films.utils.image_loader.ImageLoader
import com.example.films.utils.image_loader.ImageLoaderListener

/**
 * ViewHolder для Film
 */
class FilmViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.film_item) {
    private var binding: FilmItemBinding = FilmItemBinding.bind(itemView)
    private lateinit var film: Film
    private lateinit var listener: FilmViewHolderListener

    fun bind(
        film: Film,
        listener: FilmViewHolderListener,
        columnMarginDecorator: ColumnMarginDecorator
    ) {
        this.film = film
        this.listener = listener

        columnMarginDecorator.setColumnMargins(itemView)
        showFilmInfo()
        setListener()
    }

    private fun showFilmInfo() {
        binding.filmNameText.text = film.localized_name
        if (film.image_url.isNotEmpty())
            ImageLoader
                .load(film.image_url)
                .into(
                    binding.filmPosterImage,
                    object : ImageLoaderListener {
                        override fun onError(error: String) {
                            binding.posterNotFoundImage.isVisible = true
                        }

                        override fun onSuccess() {
                            binding.posterNotFoundImage.isVisible = false
                        }
                    }
                )
        else {
            binding.posterNotFoundImage.isVisible = true
            binding.filmPosterImage.setImageDrawable(null)
        }
    }

    private fun setListener() {
        itemView.setOnClickListener {
            listener.onFilmClick(film.filmId)
        }
    }

    /**
     * Слушатель нажатий FilmViewHolder
     */
    interface FilmViewHolderListener {
        fun onFilmClick(filmId: Int)
    }
}