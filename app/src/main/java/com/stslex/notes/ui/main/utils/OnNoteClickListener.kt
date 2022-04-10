package com.stslex.notes.ui.main.utils

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.stslex.notes.data.entity.NoteEntity
import com.stslex.notes.ui.core.ClickListener
import com.stslex.notes.ui.main.MainFragmentDirections
import com.stslex.notes.ui.model.NoteUIModel
import javax.inject.Inject

interface OnNoteClickListener : ClickListener<NoteUIModel> {

    fun createNewNote(fab: View)

    class Base @Inject constructor() : OnNoteClickListener {

        private val emptyNote by lazy {
            NoteEntity(title = "", content = "", timestamp = System.currentTimeMillis())
        }

        override fun onClick(item: NoteUIModel) {
            item.click { it.navigateToEdit(NOTE_IS_EDIT, item.id()) }
        }

        override fun createNewNote(fab: View) {
            fab.navigateToEdit(!NOTE_IS_EDIT, emptyNote.id)
        }

        private fun View.navigateToEdit(edit: Boolean, id: Int) {
            val directions = MainFragmentDirections.actionNavHomeToNavEdit(id, edit, transitionName)
            val extras = FragmentNavigatorExtras(this to transitionName)
            Navigation.findNavController(this).navigate(directions, extras)
        }
    }

    companion object {
        private const val NOTE_IS_EDIT: Boolean = true
    }
}