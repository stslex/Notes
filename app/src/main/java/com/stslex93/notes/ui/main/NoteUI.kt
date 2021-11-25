package com.stslex93.notes.ui.main

import android.widget.TextView
import com.google.android.material.card.MaterialCardView

interface NoteUI {

    fun bind(
        titleTextView: TextView,
        contentTextView: TextView,
        itemCardView: MaterialCardView
    )

    fun click(function: (MaterialCardView) -> Unit)
    fun longClick()

    fun getId(): String
    fun getContent(): String
    fun isChecked(): Boolean
    fun setChecked(isChecked: Boolean)

    data class Base(
        private val id: String,
        private val title: String,
        private val content: String,
        private val datestamp: String,
        private val timestamp: String,
        private var isChecked: Boolean = false
    ) : NoteUI {

        override fun isChecked(): Boolean = isChecked
        override fun setChecked(isChecked: Boolean) {
            this.isChecked = isChecked
        }

        private var _cardView: MaterialCardView? = null
        private val cardView: MaterialCardView get() = checkNotNull(_cardView)

        override fun bind(
            titleTextView: TextView,
            contentTextView: TextView,
            itemCardView: MaterialCardView
        ) {
            titleTextView.text = title
            contentTextView.text = content
            _cardView = itemCardView
            cardView.transitionName = id.toString()
        }

        override fun click(function: (MaterialCardView) -> Unit) = function(cardView)

        override fun longClick() {

        }

        override fun getId(): String = id
        override fun getContent(): String = content
    }
}