package com.stslex.notes.ui.core

import kotlinx.coroutines.flow.SharedFlow

interface SelectItemsUtil<T> {
    val isSelectionStart: Boolean
    val itemsSelected: SharedFlow<List<T>>
    fun deleteAll()
    fun select(item: T)
}