package com.stslex93.notes.ui.model

import android.annotation.SuppressLint
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputLayout
import com.stslex93.notes.ui.widget.ComposeCustomTextView
import java.text.SimpleDateFormat
import java.util.*

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

    fun bind(
        titleTextView: TextView,
        contentTextView: ComposeCustomTextView,
        itemCardView: MaterialCardView
    ) {
        titleTextView.text = title
        contentTextView.setText(content)
        _cardView = itemCardView
        cardView.transitionName = id.toString()
    }

    fun bindEditNote(
        titleTextView: TextInputLayout,
        contentTextView: TextInputLayout
    ) {
        titleTextView.editText?.setText(title)
        contentTextView.editText?.setText(content)
    }

    @SuppressLint("SetTextI18n")
    fun setLastEditTime(textView: TextView, labelEdit: String) {
        val currentDate = convert(DATE_FORMAT, System.currentTimeMillis())
        val date = convert(DATE_FORMAT, timestamp)
        val time = convert(TIME_FORMAT, timestamp)
        val resultTime = if (date == currentDate) time else date
        textView.text = "$labelEdit: $resultTime"
    }

    private fun convert(format: String, time: Long): String =
        SimpleDateFormat(format, Locale.getDefault()).format(time)

    fun click(function: (MaterialCardView) -> Unit) = function(cardView)

    companion object {
        private const val DATE_FORMAT = "dd.MM.yyyy"
        private const val TIME_FORMAT = "kk:mm"

        val EMPTY = NoteUIModel(0, "", "", 0)
    }
}