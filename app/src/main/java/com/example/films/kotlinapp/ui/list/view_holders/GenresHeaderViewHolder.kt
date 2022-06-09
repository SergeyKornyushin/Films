package com.example.films.kotlinapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.films.R
import com.example.films.databinding.HeaderItemBinding
import com.example.films.kotlinapp.mvp.models.entities.GenresHeader

/**
 *  ViewHolder для заголовка блока с Genres
 */
class GenresHeaderViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.header_item) {
    private var binding: HeaderItemBinding = HeaderItemBinding.bind(itemView)
    private lateinit var genresHeader: GenresHeader

    fun bind(genresHeader: GenresHeader) {
        this.genresHeader = genresHeader

        showTitleText()
    }

    private fun showTitleText() {
        binding.headerText.text = genresHeader.name
    }
}