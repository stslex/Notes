package com.stslex93.notes.core.navigation.model

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
        val noteId: Int,
        val isEdit: Boolean,
    ) : AppArguments() {
        override val arguments = listOf(noteId.toString(), isEdit.toString())
    }

    data class LabelEdit(
        val noteIds: Set<Int>
    ) : AppArguments() {
        override val arguments = listOf(noteIds.joinToString(LIST_SEPARATOR))
    }

    companion object {
        private const val ARGUMENTS_SEPARATOR = "/"
        private const val LIST_SEPARATOR = "="
    }
}