package com.stslex93.notes.ui.legacy.main.utils

import com.stslex93.notes.ui.legacy.core.LongClickListener
import com.stslex93.notes.ui.legacy.core.SelectItemsUtil
import com.stslex93.notes.ui.legacy.model.NoteUIModel


interface OnNoteLongClickListener : LongClickListener<NoteUIModel> {

    class Base(
        private val itemsSelector: SelectItemsUtil<NoteUIModel>
    ) : OnNoteLongClickListener {

        override fun click(item: NoteUIModel) {
            itemsSelector.select(item)
        }
    }
}