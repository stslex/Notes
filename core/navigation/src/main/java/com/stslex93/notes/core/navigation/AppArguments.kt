package com.stslex93.notes.core.navigation

sealed class AppArguments {

    abstract val arguments: List<String>

    open val argumentsForRoute: String
        get() = arguments.joinToString(
            separator = ARGUMENTS_SEPARATOR,
            prefix = ARGUMENTS_SEPARATOR
        )

    data object Empty : AppArguments() {
        override val arguments = emptyList<String>()
        override val argumentsForRoute = ""
    }

    data class NoteEdit(
        val noteId: String,
        val isEdit: String,
    ) : AppArguments() {
        override val arguments = listOf(noteId, isEdit)
    }

    companion object {
        private const val ARGUMENTS_SEPARATOR = "/"
    }
}