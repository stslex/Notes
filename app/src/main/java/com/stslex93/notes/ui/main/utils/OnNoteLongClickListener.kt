package com.stslex93.notes.ui.main.utils

import com.stslex93.notes.ui.core.LongClickListener
import com.stslex93.notes.ui.core.SelectItemsUtil
import com.stslex93.notes.ui.model.NoteUIModel


interface OnNoteLongClickListener : LongClickListener<NoteUIModel> {

    class Base(
        private val itemsSelector: SelectItemsUtil<NoteUIModel>
    ) : OnNoteLongClickListener {

        override fun click(item: NoteUIModel) {
            itemsSelector.select(item)
        }
    }
}