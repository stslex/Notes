package com.example.notes.ui.main

import androidx.cardview.widget.CardView
import com.example.notes.model.base.Note
import com.google.android.material.card.MaterialCardView

class ItemClickListener(val clickListener: (MaterialCardView, Note, String) -> Unit,
                        val onLongClickListener: (MaterialCardView, Note) -> Unit) {

    fun onClick(
        card: MaterialCardView,
        note: Note,
        key: String) = clickListener(card, note, key)

    fun onClickLong(
        card: MaterialCardView,
        note: Note
    ) = onLongClickListener(card, note)

}