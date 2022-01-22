package com.stslex93.notes.ui.main

import com.stslex93.notes.ui.core.LongClickListener
import com.stslex93.notes.ui.model.NoteUI
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject


interface OnNoteLongClickListener : LongClickListener<NoteUI> {

    val itemsSelected: SharedFlow<List<NoteUI>>
    fun deleteAll()
    fun selectAll(list: List<NoteUI>)

    class Base @Inject constructor() : OnNoteLongClickListener {

        private val listOfCards = mutableListOf<NoteUI>()
        private var _itemsSelected: MutableSharedFlow<List<NoteUI>> = MutableSharedFlow(
            replay = 1,
            extraBufferCapacity = 0,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
        
        override val itemsSelected: SharedFlow<List<NoteUI>>
            get() = _itemsSelected.asSharedFlow()

        init {
            _itemsSelected.tryEmit(emptyList())
        }

        override fun deleteAll() {
            listOfCards.forEach {
                it.setChecked(false)
            }
            listOfCards.clear()
            _itemsSelected.tryEmit(listOfCards)
        }

        override fun selectAll(list: List<NoteUI>) {
            listOfCards.clear()
            listOfCards.addAll(list)
            _itemsSelected.tryEmit(list)
        }

        override fun click(item: NoteUI) {
            item.setChecked(!item.isChecked())
            if (item.isChecked()) listOfCards.add(item) else listOfCards.remove(item)
            _itemsSelected.tryEmit(listOfCards)
        }
    }
}