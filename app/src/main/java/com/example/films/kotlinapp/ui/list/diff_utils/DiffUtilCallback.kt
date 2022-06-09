package com.example.films.kotlinapp.ui.list.diff_utils

import androidx.recyclerview.widget.DiffUtil
import com.example.films.kotlinapp.ui.list.ListItem

/**
 * Callback DiffUtils для RVAdapter
 */
class DiffUtilCallback(private val oldItems: List<ListItem>, private val newItems: List<ListItem>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItems[oldItemPosition] == newItems[newItemPosition]
}