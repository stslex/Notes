package com.stslex93.notes.ui.main

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.ui.core.ClickListener
import javax.inject.Inject

interface OnNoteClickListener : ClickListener<NoteUI> {

    fun createNewNote(fab: View)

    class Base @Inject constructor() : OnNoteClickListener {

        override fun onClick(item: NoteUI) {
            item.click { it.navigateToEdit(EDIT, it.transitionName) }
        }

        override fun createNewNote(fab: View) {
            val note = Note(title = "", content = "", datestamp = "", timestamp = "")
            fab.navigateToEdit(NOT_EDIT, note.id.toString())
        }

        private fun View.navigateToEdit(edit: Boolean, id: String) {
            val directions = MainFragmentDirections.actionNavHomeToNavEdit(id, edit, transitionName)
            val extras = FragmentNavigatorExtras(this to transitionName)
            Navigation.findNavController(this).navigate(directions, extras)
        }
    }

    companion object {
        private const val EDIT: Boolean = true
        private const val NOT_EDIT: Boolean = false
    }
}