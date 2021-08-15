package com.stslex93.notes.utilites.clicker

import com.google.android.material.card.MaterialCardView

class ItemClickListener(
    val clickListener: (MaterialCardView, String) -> Unit,
    val onLongClickListener: (MaterialCardView, String) -> Unit
) {
    fun onClick(
        card: MaterialCardView,
        id: String
    ) = clickListener(card, id)

    fun onClickLong(
        card: MaterialCardView,
        id: String
    ) = onLongClickListener(card, id)
}