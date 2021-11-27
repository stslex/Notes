package com.stslex93.notes.ui.model

import android.annotation.SuppressLint
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputLayout
import com.stslex93.notes.data.model.NoteData
import java.text.SimpleDateFormat
import java.util.*

interface NoteUI {

    fun bind(
        titleTextView: TextView,
        contentTextView: TextView,
        itemCardView: MaterialCardView
    )

    fun bindEditNote(titleTextView: TextInputLayout, contentTextView: TextInputLayout)
    fun setLastEditTime(textView: TextView, labelEdit: String)
    fun click(function: (MaterialCardView) -> Unit)
    fun longClick()

    fun getId(): Int
    fun getTitle(): String
    fun getContent(): String
    fun getTimestamp(): Long
    fun isChecked(): Boolean
    fun setChecked(isChecked: Boolean)
    fun mapToData(mapper: NoteUIDataMapper): NoteData

    data class Base(
        private val id: Int,
        private val title: String,
        private val content: String,
        private val timestamp: Long,
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

        override fun bindEditNote(
            titleTextView: TextInputLayout,
            contentTextView: TextInputLayout
        ) {
            titleTextView.editText?.setText(title)
            contentTextView.editText?.setText(content)
        }

        @SuppressLint("SetTextI18n")
        override fun setLastEditTime(textView: TextView, labelEdit: String) {
            val currentDate = convert(DATE_FORMAT, System.currentTimeMillis())
            val date = convert(DATE_FORMAT, timestamp)
            val time = convert(TIME_FORMAT, timestamp)
            val resultTime = if (date == currentDate) time else date
            textView.text = "$labelEdit: $resultTime"
        }

        private fun convert(format: String, time: Long): String =
            SimpleDateFormat(format, Locale.getDefault()).format(time)

        override fun click(function: (MaterialCardView) -> Unit) = function(cardView)
        override fun longClick() = Unit
        override fun getId(): Int = id
        override fun getTitle(): String = title
        override fun getContent(): String = content
        override fun getTimestamp(): Long = timestamp
        override fun mapToData(mapper: NoteUIDataMapper) = mapper.map(this)

        companion object {
            private const val DATE_FORMAT = "dd.MM.yyyy"
            private const val TIME_FORMAT = "kk:mm"
        }
    }
}