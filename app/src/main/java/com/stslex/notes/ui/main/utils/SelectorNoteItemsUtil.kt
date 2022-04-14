package com.stslex.notes.ui.main.utils

import com.stslex.notes.ui.core.SelectItemsUtil
import com.stslex.notes.ui.model.NoteUIModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

interface SelectorNoteItemsUtil : SelectItemsUtil<NoteUIModel> {

    class Base @Inject constructor() : SelectorNoteItemsUtil {

        private val listOfItems = mutableListOf<NoteUIModel>()

        private var _isSelectionStart: Boolean = false

        override val isSelectionStart: Boolean
            get() = _isSelectionStart

        private val _itemsSelected: MutableSharedFlow<List<NoteUIModel>> = MutableSharedFlow(
            replay = 1,
            extraBufferCapacity = 0,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

        init {
            _itemsSelected.tryEmit(emptyList())
        }

        override val itemsSelected: SharedFlow<List<NoteUIModel>>
            get() = _itemsSelected

        private val _lastSelectedItems: MutableList<NoteUIModel> = mutableListOf()
        override val lastSelectedItems: List<NoteUIModel>
            get() = _lastSelectedItems

        override fun deleteAll() {
            listOfItems.forEach {
                it.setChecked(false)
            }
            listOfItems.clear()
            _isSelectionStart = false
            _itemsSelected.tryEmit(emptyList())
        }

        override fun select(item: NoteUIModel) {
            item.setChecked(!item.isChecked())
            if (item.isChecked()) listOfItems.add(item) else listOfItems.remove(item)
            _lastSelectedItems.clear()
            _lastSelectedItems.addAll(listOfItems)
            _isSelectionStart = listOfItems.isNotEmpty()
            _itemsSelected.tryEmit(listOfItems)
        }
    }
}