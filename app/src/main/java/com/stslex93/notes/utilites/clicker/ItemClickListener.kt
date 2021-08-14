package com.stslex93.notes.utilites.clicker

import com.google.android.material.card.MaterialCardView
import com.stslex93.notes.data.entity.Note

class ItemClickListener(
    val clickListener: (MaterialCardView, Note) -> Unit,
    val onLongClickListener: (MaterialCardView, Note) -> Unit
) {
    fun onClick(
        card: MaterialCardView,
        note: Note
    ) = clickListener(card, note)

    fun onClickLong(
        card: MaterialCardView,
        note: Note
    ) = onLongClickListener(card, note)
}