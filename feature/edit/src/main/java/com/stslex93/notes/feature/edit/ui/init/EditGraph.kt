package com.stslex93.notes.feature.edit.ui.init

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex93.notes.core.navigation.model.AppArguments
import com.stslex93.notes.core.navigation.model.AppDestination
import com.stslex93.notes.core.navigation.utils.NavExt.composableArguments
import com.stslex93.notes.core.navigation.utils.NavExt.parseArguments
import com.stslex93.notes.feature.edit.di.setupComponent

fun NavGraphBuilder.editGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.NOTE_EDIT.navigationRoute,
        arguments = AppDestination.NOTE_EDIT.composableArguments
    ) { navBackStackEntry ->

        val arguments = AppDestination.NOTE_EDIT
            .parseArguments(navBackStackEntry)
            .let { args ->
                AppArguments.NoteEdit(
                    noteId = args[0].toIntOrNull() ?: -1,
                    isEdit = args[1].toBooleanStrictOrNull() ?: false
                )
            }

        val viewModel = setupComponent(arguments.hashCode().toString())

        LaunchedEffect(Unit) {
            viewModel.init(arguments)
        }

        InitEditScreen(
            modifier = modifier,
            viewModel = viewModel
        )
    }
}