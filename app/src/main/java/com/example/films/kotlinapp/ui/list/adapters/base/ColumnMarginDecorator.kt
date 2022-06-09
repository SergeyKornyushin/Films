package com.example.films.kotlinapp.ui.list.adapters.base

import android.view.View
import android.view.ViewGroup
import com.example.films.kotlinapp.ui.constants.UiConstants.DEFAULT_BOTTOM_MARGIN
import com.example.films.kotlinapp.ui.constants.UiConstants.DEFAULT_HORIZONTAL_MARGIN
import com.example.films.kotlinapp.ui.constants.UiConstants.DEFAULT_TOP_MARGIN
import com.example.films.kotlinapp.ui.constants.UiConstants.SPAN_COUNT
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

        val right = DEFAULT_HORIZONTAL_MARGIN - column * DEFAULT_HORIZONTAL_MARGIN / SPAN_COUNT
        val left = (column + 1) * DEFAULT_HORIZONTAL_MARGIN / SPAN_COUNT

        val layoutParams = itemView.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.setMargins(left, DEFAULT_TOP_MARGIN, right, DEFAULT_BOTTOM_MARGIN)
        itemView.layoutParams = layoutParams
    }
}