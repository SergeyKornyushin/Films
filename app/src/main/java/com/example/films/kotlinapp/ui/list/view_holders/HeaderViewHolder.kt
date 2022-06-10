package com.example.films.kotlinapp.ui.list.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.films.R
import com.example.films.databinding.HeaderItemBinding
import com.example.films.kotlinapp.ui.list.ListItem
import com.example.films.kotlinapp.ui.list.view_holders.base.BaseViewHolder

/**
 *  ViewHolder для заголовка блока с Genres
 */
class HeaderViewHolder(
    layoutInflater: LayoutInflater,
    parent: ViewGroup
) : BaseViewHolder(layoutInflater, parent, R.layout.header_item) {
    private var binding: HeaderItemBinding = HeaderItemBinding.bind(itemView)
    private lateinit var listItem: ListItem

    fun bind(listItem: ListItem) {
        this.listItem = listItem

        showTitleText()
    }

    private fun showTitleText() {
        binding.headerText.text = listItem.data.toString()
    }
}