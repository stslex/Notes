package com.stslex93.notes.core.navigation.model

import com.stslex93.notes.core.ui.di.Screen

sealed class NavigationScreen : Screen {

    abstract val screen: AppDestination

    val screenRoute: String
        get() = "${screen.route}${appArgs.argumentsForRoute}"

    open val isSingleTop: Boolean
        get() = false

    open val appArgs: AppArguments
        get() = AppArguments.Empty

    data object HomeScreen : NavigationScreen() {
        override val screen: AppDestination = AppDestination.HOME
        override val isSingleTop: Boolean = true
    }

    data class EditNoteScreen(
        private val noteId: Int,
        private val isEdit: Boolean
    ) : NavigationScreen() {
        override val screen: AppDestination = AppDestination.NOTE_EDIT
        override val appArgs: AppArguments = AppArguments.NoteEdit(noteId, isEdit)
    }

    data class EditLabelScreen(
        private val noteIds: Set<Int>
    ) : NavigationScreen() {
        override val screen = AppDestination.LABEL_EDIT
        override val appArgs = AppArguments.LabelEdit(noteIds)
    }

    data object PopBackStack : NavigationScreen() {

        override val screen: AppDestination = AppDestination.UNDEFINED
        override val appArgs: AppArguments = AppArguments.Empty
    }
}