package com.stslex93.notes.ui.main.utils

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.stslex93.notes.data.entity.NoteEntity
import com.stslex93.notes.ui.core.ClickListener
import com.stslex93.notes.ui.main.MainFragmentDirections
import com.stslex93.notes.ui.model.NoteUI
import javax.inject.Inject

interface OnNoteClickListener : ClickListener<NoteUI> {

    fun createNewNote(fab: View)

    class Base @Inject constructor() : OnNoteClickListener {

        private val emptyNote by lazy {
            NoteEntity(title = "", content = "", timestamp = System.currentTimeMillis())
        }

        override fun onClick(item: NoteUI) {
            item.click { it.navigateToEdit(NOTE_IS_EDIT, item.getId()) }
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