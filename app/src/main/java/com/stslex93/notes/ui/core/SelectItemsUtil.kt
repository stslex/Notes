package com.stslex93.notes.ui.core

import kotlinx.coroutines.flow.SharedFlow

interface SelectItemsUtil<T> {
    val isSelectionStart: Boolean
    val itemsSelected: SharedFlow<List<T>>
    val lastSelectedItems: List<T>
    suspend fun deleteAll()
    fun select(item: T)
}