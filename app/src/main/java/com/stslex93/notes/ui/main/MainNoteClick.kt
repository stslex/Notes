package com.stslex93.notes.ui.main

import com.google.android.material.card.MaterialCardView
import com.stslex93.notes.data.entity.Note

open class MainNoteClick {
    val checkNotes = mutableListOf<Note>()
    val checkCards = mutableListOf<MaterialCardView>()

    inline fun cardCheckRemove(
        card: MaterialCardView,
        note: Note,
        crossinline function: () -> Unit
    ) {
        card.isChecked = false
        checkCards.remove(card)
        checkNotes.remove(note)
        if (checkCards.isEmpty()) {
            function()
        }
    }

    fun cardCheckAdd(card: MaterialCardView, note: Note) {
        card.isChecked = true
        checkCards.add(card)
        checkNotes.add(note)
    }

}