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

    fun getId(): String
    fun getTitle(): String
    fun getContent(): String
    fun getDatestamp(): String
    fun getTimestamp(): String
    fun isChecked(): Boolean
    fun setChecked(isChecked: Boolean)
    fun mapToData(mapper: NoteUIDataMapper): NoteData

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
            cardView.transitionName = id
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
            val checkDate = SimpleDateFormat(
                DATE_FORMAT,
                Locale.getDefault()
            ).format(System.currentTimeMillis())
            val time = if (datestamp == checkDate)
                convert(TIME_FORMAT, timestamp)
            else datestamp
            textView.text = "$labelEdit: $time"
        }

        private fun convert(format: String, time: String): String =
            SimpleDateFormat(format, Locale.getDefault()).format(time)


        override fun click(function: (MaterialCardView) -> Unit) = function(cardView)
        override fun longClick() = Unit
        override fun getId(): String = id
        override fun getTitle(): String = title
        override fun getContent(): String = content
        override fun getDatestamp(): String = datestamp
        override fun getTimestamp(): String = timestamp
        override fun mapToData(mapper: NoteUIDataMapper) = mapper.map(this)

        companion object {
            private const val DATE_FORMAT = "dd.MM.yyyy"
            private const val TIME_FORMAT = "kk:mm"
        }
    }
}