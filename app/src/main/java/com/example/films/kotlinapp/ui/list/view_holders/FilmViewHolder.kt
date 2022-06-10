package com.example.films.kotlinapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.films.R
import com.example.films.databinding.FilmItemBinding
import com.example.films.kotlinapp.ui.list.ListItem
import com.example.films.kotlinapp.ui.list.TitledImageData
import com.example.films.kotlinapp.ui.list.adapters.base.ColumnMarginDecorator
import com.example.films.kotlinapp.ui.list.view_holders.base.BaseViewHolder
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
    private lateinit var listItem: ListItem
    private lateinit var listener: FilmViewHolderListener

    fun bind(
        listItem: ListItem,
        listener: FilmViewHolderListener,
        columnMarginDecorator: ColumnMarginDecorator
    ) {
        this.listItem = listItem
        this.listener = listener

        columnMarginDecorator.setColumnMargins(itemView)
        showFilmInfo()
        setListener()
    }

    private fun showFilmInfo() {
        binding.filmNameText.text = (listItem.data as TitledImageData).title
        if ((listItem.data as TitledImageData).imageUrl.isNotEmpty())
            ImageLoader
                .load((listItem.data as TitledImageData).imageUrl)
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
            listener.onFilmClick((listItem.data as TitledImageData).id)
        }
    }

    /**
     * Слушатель нажатий FilmViewHolder
     */
    interface FilmViewHolderListener {
        fun onFilmClick(filmId: Int)
    }
}