package com.stslex93.notes.core.navigation.v2.screen

import com.stslex93.notes.core.navigation.v2.NoteEditArgs
import com.stslex93.notes.core.ui.base.NavigationScreen
import com.stslex93.notes.core.ui.base.saveBundle

data object Home : NavigationScreen(HostScreen.HOME.route) {

    override val isSingleTop: Boolean = true
}

data class NoteEdit(
    val noteEdit: NoteEditArgs
) : NavigationScreen(
    route = HostScreen.NOTE_EDIT.route,
    extra = Extra(HostScreen.NOTE_EDIT.key, noteEdit)
)

//sealed class AppNavScreen(
//    route: String,
//    extra: Extra? = null
//) : NavigationScreen(route, extra.saveBundle) {
//
//
//}