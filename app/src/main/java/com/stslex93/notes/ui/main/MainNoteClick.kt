package com.stslex93.notes.ui.main

import com.google.android.material.card.MaterialCardView

open class MainNoteClick {
    val checkNotes = mutableListOf<String>()
    val checkCards = mutableListOf<MaterialCardView>()

    inline fun cardCheckRemove(
        card: MaterialCardView,
        id: String,
        crossinline function: () -> Unit
    ) {
        card.isChecked = false
        checkCards.remove(card)
        checkNotes.remove(id)
        if (checkCards.isEmpty()) {
            function()
        }
    }

    fun cardCheckAdd(card: MaterialCardView, id: String) {
        card.isChecked = true
        checkCards.add(card)
        checkNotes.add(id)
    }


}