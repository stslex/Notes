package com.stslex93.notes.feature.edit.ui.init

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.stslex93.notes.core.navigation.v2.NoteEditArgs
import com.stslex93.notes.core.navigation.v2.compose.composable
import com.stslex93.notes.core.navigation.v2.screen.HostScreen
import com.stslex93.notes.feature.edit.di.setupComponent

fun NavGraphBuilder.editGraph(
    modifier: Modifier = Modifier,
) {
    composable<NoteEditArgs>(HostScreen.NOTE_EDIT) { arguments ->

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