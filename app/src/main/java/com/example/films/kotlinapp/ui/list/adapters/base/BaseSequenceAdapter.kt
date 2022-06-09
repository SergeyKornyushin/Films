package com.example.films.kotlinapp.ui.list.adapters.base

import android.view.LayoutInflater
import com.example.films.kotlinapp.ui.list.view_holders.BaseViewHolder

/**
 * Базовый Adapter c фиксированной последовательностью типов ViewHolder
 *
 * @param <ItemClass> класс элементов списка
 * @param <VH>        класс ViewHolder
 */
abstract class BaseSequenceAdapter<ItemClass, VH : BaseViewHolder>(
    layoutInflater: LayoutInflater,
) : BaseAdapter<ItemClass, VH>(layoutInflater) {

    /**
     * Последовательность типов
     */
    protected abstract val typesSequence: IntArray

    /**
     * Ключ - тип ViewHolder, значение - порядковый номер типа ViewHolder.
     * Порядковый номер определяется последовательностью типов ({@link #getTypeSequence()}).
     * Эта структура нужна для быстрого доступа к порядковым номерам без необходимости
     * перебора последовательности ({@link #getTypeSequence()}).
     */
    private var typeIndexes: MutableMap<Int, Int> = mutableMapOf()

    init {
        for (i in typesSequence.indices) {
            typeIndexes[typesSequence[i]] = i
        }
    }

    abstract fun getItemViewType(item: ItemClass): Int

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return getItemViewType(item)
    }

    /**
     * Задавание элемента в списке по типу.
     *
     * @param item элемент, который необходимо задать
     * @param type тип ViewHolder
     */
    protected fun updateItem(item: ItemClass, type: Int) {
        updateItem(item, getDesiredItemPosition(type), type)
    }

    /**
     * Задавание элементов в списке по типу.
     *
     * @param objects объекты, которые необходимо задать
     * @param type    тип ViewHolder
     */
    protected fun updateItems(objects: List<ItemClass>, type: Int) {
        removeItemsByTypes(type)
        addItems(objects, type)
    }

    /**
     * Получить желаемую позицию элемента по типу.
     * Желаемая = фактической, если элемент есть в списке.
     *
     * @param type тип ViewHolder
     * @return позиция элемента
     */
    private fun getDesiredItemPosition(type: Int): Int {
        items.forEachIndexed { index, item ->
            val currentType = getItemViewType(item)
            val currentTypeIndex = typeIndexes[currentType]
            val typeIndex = typeIndexes[type]
            if (currentTypeIndex!! >= typeIndex!!) {
                return index
            }
        }

        return itemCount
    }

    /**
     * Добавление элементов в список по типу
     *
     * @param addingItems добавляемые элементы
     * @param type        тип ViewHolder
     */
    private fun addItems(addingItems: List<ItemClass>, type: Int) {
        val addingPosition = getDesiredItemPosition(type)
        items.addAll(addingPosition, addingItems)
        notifyItemRangeInserted(addingPosition, addingItems.size)
    }
}