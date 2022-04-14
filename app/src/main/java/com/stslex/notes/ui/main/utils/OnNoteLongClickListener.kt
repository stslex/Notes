package com.stslex.notes.ui.main.utils

import com.stslex.notes.ui.core.LongClickListener
import com.stslex.notes.ui.core.SelectItemsUtil
import com.stslex.notes.ui.model.NoteUIModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


interface OnNoteLongClickListener : LongClickListener<NoteUIModel> {

    class Base @AssistedInject constructor(
        @Assisted("itemsSelector") private val itemsSelector: SelectItemsUtil<NoteUIModel>
    ) : OnNoteLongClickListener {

        override fun click(item: NoteUIModel) {
            itemsSelector.select(item)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("itemsSelector") itemsSelector: SelectItemsUtil<NoteUIModel>): Base
    }
}