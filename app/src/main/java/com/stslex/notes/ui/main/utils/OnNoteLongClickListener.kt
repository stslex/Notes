package com.stslex.notes.ui.main.utils

import com.stslex.notes.ui.core.LongClickListener
import com.stslex.notes.ui.model.NoteUIModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject


interface OnNoteLongClickListener : LongClickListener<NoteUIModel> {

    val itemsSelected: SharedFlow<List<NoteUIModel>>
    fun deleteAll()
    fun selectAll(list: List<NoteUIModel>)

    class Base @Inject constructor() : OnNoteLongClickListener {

        private val listOfCards = mutableListOf<NoteUIModel>()
        private var _itemsSelected: MutableSharedFlow<List<NoteUIModel>> = MutableSharedFlow(
            replay = 1,
            extraBufferCapacity = 0,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )

        override val itemsSelected: SharedFlow<List<NoteUIModel>>
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

        override fun selectAll(list: List<NoteUIModel>) {
            listOfCards.clear()
            listOfCards.addAll(list)
            _itemsSelected.tryEmit(list)
        }

        override fun click(item: NoteUIModel) {
            item.setChecked(!item.isChecked())
            if (item.isChecked()) listOfCards.add(item) else listOfCards.remove(item)
            _itemsSelected.tryEmit(listOfCards)
        }
    }
}