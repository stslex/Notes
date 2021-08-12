package com.stslex93.notes.ui.main.adapter

import com.google.android.material.card.MaterialCardView
import com.stslex93.notes.data.model.Note

class ItemClickListener(
    val clickListener: (MaterialCardView, Note, String) -> Unit,
    val onLongClickListener: (MaterialCardView, Note) -> Unit
) {
    fun onClick(
        card: MaterialCardView,
        note: Note,
        key: String
    ) = clickListener(card, note, key)

    fun onClickLong(
        card: MaterialCardView,
        note: Note
    ) = onLongClickListener(card, note)
}