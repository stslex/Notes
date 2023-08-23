package com.stslex93.notes.ui.legacy.model

import com.google.android.material.card.MaterialCardView

data class NoteUIModel(
    val id: Int,
    val title: String,
    val content: String,
    val timestamp: Long,
) {
    var isChecked: Boolean = false

    fun updateViewCheck() {
        cardView.isChecked = isChecked
    }

    private var _cardView: MaterialCardView? = null
    private val cardView: MaterialCardView get() = checkNotNull(_cardView)

    // TODO remove
    fun setCardView(cardView: MaterialCardView) {
        _cardView = cardView
    }

    fun click(function: (MaterialCardView) -> Unit) = function(cardView)
}