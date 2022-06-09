package com.example.films.kotlinapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.films.R
import com.example.films.databinding.HeaderItemBinding
import com.example.films.kotlinapp.mvp.models.entities.FilmsHeader

/**
 * ViewHolder для заголовка блока с Films
 */
class FilmsHeaderViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.header_item) {
    private var binding: HeaderItemBinding = HeaderItemBinding.bind(itemView)
    private lateinit var filmsHeader: FilmsHeader

    fun bind(filmsHeader: FilmsHeader) {
        this.filmsHeader = filmsHeader

        showTitleText()
    }

    private fun showTitleText() {
        binding.headerText.text = filmsHeader.name
    }
}