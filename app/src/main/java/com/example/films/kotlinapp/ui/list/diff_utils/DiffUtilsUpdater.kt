package com.example.films.kotlinapp.ui.list.diff_utils

/**
 * Обновление списка с помощью DiffUtils
 */
interface DiffUtilsUpdater<T> {

    /**
     * Обновить список с помощью DiffUtils
     */
    fun updateWithDiffUtils(films: List<T>)
}