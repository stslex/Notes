package st.slex.csplashscreen.core.navigation

import com.stslex93.notes.core.navigation.AppArguments
import com.stslex93.notes.core.navigation.AppDestination

sealed class NavigationScreen {

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
        private val noteId: String,
        private val isEdit: String
    ) : NavigationScreen() {
        override val screen: AppDestination = AppDestination.NOTE_EDIT
        override val appArgs: AppArguments = AppArguments.NoteEdit(noteId, isEdit)
    }

    data object PopBackStack : NavigationScreen() {

        override val screen: AppDestination = AppDestination.UNDEFINED
        override val appArgs: AppArguments = AppArguments.Empty
    }
}