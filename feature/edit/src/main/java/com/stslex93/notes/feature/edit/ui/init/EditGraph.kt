package com.stslex93.notes.feature.edit.ui.init

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.stslex93.notes.core.navigation.model.AppDestination
import com.stslex93.notes.core.navigation.utils.NavExt.composableArguments
import com.stslex93.notes.core.navigation.v2.NoteEditArgs
import com.stslex93.notes.core.navigation.v2.compose.composableArg
import com.stslex93.notes.core.navigation.v2.screen.HostScreen
import com.stslex93.notes.feature.edit.di.setupComponent

fun NavGraphBuilder.editGraph(
    modifier: Modifier = Modifier,
) {
    composableArg<NoteEditArgs>(HostScreen.NOTE_EDIT) { noteEditArgs ->
        val arguments = noteEditArgs ?: NoteEditArgs(-1, false)

        val viewModel = setupComponent(arguments.hashCode().toString())

        LaunchedEffect(Unit) {
            viewModel.init(arguments)
        }

        InitEditScreen(
            modifier = modifier,
            viewModel = viewModel
        )
    }
//    composable(
//        route = AppDestination.NOTE_EDIT.navigationRoute,
//        arguments = AppDestination.NOTE_EDIT.composableArguments
//    ) { navBackStackEntry ->
//
//        val arguments = AppDestination.NOTE_EDIT
//            .parseArguments(navBackStackEntry)
//            .let { args ->
//                AppArguments.NoteEdit(
//                    noteId = args[0].toIntOrNull() ?: -1,
//                    isEdit = args[1].toBooleanStrictOrNull() ?: false
//                )
//            }
//
//        val viewModel = setupComponent(arguments.hashCode().toString())
//
//        LaunchedEffect(Unit) {
//            viewModel.init(arguments)
//        }
//
//        InitEditScreen(
//            modifier = modifier,
//            viewModel = viewModel
//        )
//    }
}