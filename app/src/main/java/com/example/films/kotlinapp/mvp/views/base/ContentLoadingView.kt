package com.example.films.kotlinapp.mvp.views.base

import com.example.films.kotlinapp.mvp.views.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

/**
 * Интерфейс для экраном с загрузкой данных
 */
interface ContentLoadingView : BaseView {

    /**
     * Начата загрузка контента
     *
     * Очищает весь ViewState
     */
    @StateStrategyType(value = SingleStateStrategy::class, tag = "contentLoading")
    fun startContentLoading()

    /**
     * Загрузка контента завершена
     */
    @StateStrategyType(value = AddToEndSingleTagStrategy::class, tag = "contentLoading")
    fun endContentLoading()

    /**
     * Показать ошибку контента
     *
     * @param error текст ошибки
     */
    @StateStrategyType(AddToEndSingleTagStrategy::class)
    fun showContentLoadingError(error: String)
}