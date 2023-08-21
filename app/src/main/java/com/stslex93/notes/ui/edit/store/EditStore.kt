package com.stslex93.notes.ui.edit.store

import com.stslex93.notes.core.ui.base.store.Store
import com.stslex93.notes.ui.edit.model.Note
import com.stslex93.notes.ui.edit.store.EditStore.Action
import com.stslex93.notes.ui.edit.store.EditStore.Event
import com.stslex93.notes.ui.edit.store.EditStore.State
import java.text.SimpleDateFormat
import java.util.Locale

interface EditStore : Store<State, Event, Action> {

    data class State(
        val note: Note
    ) : Store.State {

        val timeString: String
            get() {
                val currentDate = convert(DATE_FORMAT, System.currentTimeMillis())
                val date = convert(DATE_FORMAT, note.timestamp)
                val time = convert(TIME_FORMAT, note.timestamp)
                return if (date == currentDate) time else date
            }

        private fun convert(format: String, time: Long): String =
            SimpleDateFormat(format, Locale.getDefault()).format(time)

        companion object {
            private const val DATE_FORMAT = "dd.MM.yyyy"
            private const val TIME_FORMAT = "kk:mm"
        }
    }

    sealed interface Event : Store.Event

    sealed interface Action : Store.Action {

        data class Init(
            val id: Int,
            val isEdit: Boolean
        ) : Action

        data class InputTitle(
            val title: String
        ) : Action

        data class InputContent(
            val content: String
        ) : Action

        data object SaveNote : Action
    }
}