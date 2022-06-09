package com.example.films.kotlinapp.ui.list.adapters.base

import android.view.View
import android.view.ViewGroup
import com.example.films.kotlinapp.ui.list.ListItem
import com.example.films.kotlinapp.ui.list.Settings

/**
 * Класс для создания равных отступов в
 * RecyclerView с GridLayout
 */
data class ColumnMarginDecorator(
    private val items: List<ListItem>,
    private val position: Int
) {
    fun setColumnMargins(itemView: View) {
        val column = position % SPAN_COUNT

        items[position].settings = Settings(column)

        val right = HORIZONTAL_MARGIN - column * HORIZONTAL_MARGIN / SPAN_COUNT
        val left = (column + 1) * HORIZONTAL_MARGIN / SPAN_COUNT

        val layoutParams = itemView.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(left, TOP_MARGIN, right, BOTTOM_MARGIN)
        itemView.layoutParams = layoutParams
    }

    private companion object {
        const val SPAN_COUNT = 2
        const val HORIZONTAL_MARGIN = 22
        const val TOP_MARGIN = 22
        const val BOTTOM_MARGIN = 0
    }
}