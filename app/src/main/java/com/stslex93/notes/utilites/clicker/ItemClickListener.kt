package com.stslex93.notes.utilites.clicker

import com.google.android.material.card.MaterialCardView

class ItemClickListener(
    val clickListener: (MaterialCardView) -> Unit,
    val onLongClickListener: (MaterialCardView) -> Unit
) {
    fun onClick(
        card: MaterialCardView
    ) = clickListener(card)

    fun onClickLong(
        card: MaterialCardView
    ) = onLongClickListener(card)
}