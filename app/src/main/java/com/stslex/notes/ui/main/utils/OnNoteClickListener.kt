package com.stslex.notes.ui.main.utils

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.stslex.notes.data.entity.NoteEntity
import com.stslex.notes.ui.core.ClickListener
import com.stslex.notes.ui.core.SelectItemsUtil
import com.stslex.notes.ui.main.MainFragmentDirections
import com.stslex.notes.ui.model.NoteUIModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

interface OnNoteClickListener : ClickListener<NoteUIModel> {

    fun createNewNote(fab: View)

    class Base @AssistedInject constructor(
        @Assisted("itemsSelector") private val itemsSelector: SelectItemsUtil<NoteUIModel>
    ) : OnNoteClickListener {

        private val emptyNote by lazy {
            NoteEntity(title = "", content = "", timestamp = System.currentTimeMillis())
        }

        override fun onClick(item: NoteUIModel) {
            if (itemsSelector.isSelectionStart) {
                itemsSelector.select(item)
            } else {
                item.click { it.navigateToEdit(true, item.id()) }
            }
        }

        override fun createNewNote(fab: View) {
            fab.navigateToEdit(false, emptyNote.id)
        }

        private fun View.navigateToEdit(edit: Boolean, id: Int) {
            val directions = MainFragmentDirections.actionNavHomeToNavEdit(id, edit, transitionName)
            val extras = FragmentNavigatorExtras(this to transitionName)
            Navigation.findNavController(this).navigate(directions, extras)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("itemsSelector") itemsSelector: SelectItemsUtil<NoteUIModel>): Base
    }
}