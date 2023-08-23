package com.stslex93.notes.ui.legacy.main.utils

import android.view.View
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.stslex93.notes.core.database.NoteEntity
import com.stslex93.notes.ui.legacy.core.ClickListener
import com.stslex93.notes.ui.legacy.core.SelectItemsUtil
import com.stslex93.notes.ui.legacy.model.NoteUIModel

interface OnNoteClickListener : ClickListener<NoteUIModel> {

    fun createNewNote(fab: View)

    class Base(
        private val itemsSelector: SelectItemsUtil<NoteUIModel>
    ) : OnNoteClickListener {

        private val emptyNote by lazy {
            NoteEntity(title = "", content = "", timestamp = System.currentTimeMillis())
        }

        override fun onClick(item: NoteUIModel) {
            if (itemsSelector.isSelectionStart) {
                itemsSelector.select(item)
            } else {
                item.click { it.navigateToEdit(true, item.id) }
            }
        }

        override fun createNewNote(fab: View) {
            fab.navigateToEdit(false, emptyNote.id)
        }

        private fun View.navigateToEdit(edit: Boolean, id: Int) {
            val extras = FragmentNavigatorExtras(this to transitionName)
        }
    }
}