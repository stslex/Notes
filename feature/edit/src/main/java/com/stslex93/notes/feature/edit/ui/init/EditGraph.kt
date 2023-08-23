package com.stslex93.notes.feature.edit.ui.init

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex93.notes.core.navigation.AppArguments
import com.stslex93.notes.core.navigation.AppDestination
import com.stslex93.notes.core.navigation.NavExt.composableArguments
import com.stslex93.notes.core.navigation.NavExt.parseArguments
import com.stslex93.notes.feature.edit.ui.EditNoteViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.editGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.NOTE_EDIT.navigationRoute,
        arguments = AppDestination.NOTE_EDIT.composableArguments
    ) { navBackStackEntry ->
        val arguments = AppDestination.NOTE_EDIT.parseArguments(navBackStackEntry).let { args ->
            AppArguments.NoteEdit(
                noteId = args[0].toInt(),
                isEdit = args[1].toBooleanStrict()
            )
        }

        val viewModel: EditNoteViewModel = koinViewModel(
            key = arguments.hashCode().toString()
        ) { parametersOf(arguments) }

        InitEditScreen(
            modifier = modifier,
            viewModel = viewModel
        )
    }
}