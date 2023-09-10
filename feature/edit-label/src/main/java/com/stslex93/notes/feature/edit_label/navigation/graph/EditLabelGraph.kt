package com.stslex93.notes.feature.edit_label.navigation.graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex93.notes.core.navigation.model.AppArguments
import com.stslex93.notes.core.navigation.model.AppDestination
import com.stslex93.notes.core.navigation.utils.NavExt.composableArguments
import com.stslex93.notes.core.navigation.utils.NavExt.parseArguments
import com.stslex93.notes.core.ui.CollectAsEvent
import com.stslex93.notes.feature.edit_label.di.initComponent
import com.stslex93.notes.feature.edit_label.ui.EditLabelScreen
import com.stslex93.notes.feature.edit_label.ui.store.EditLabelStore

fun NavGraphBuilder.editLabelGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.LABEL_EDIT.navigationRoute,
        arguments = AppDestination.LABEL_EDIT.composableArguments
    ) { navBackStackEntry ->

        val arguments = AppDestination.LABEL_EDIT
            .parseArguments(navBackStackEntry)
            .firstOrNull()
            .orEmpty()

        val viewModel = initComponent(arguments)
        val noteIds = arguments
            .split(AppArguments.LIST_SEPARATOR)
            .map {
                it.toInt()
            }

        LaunchedEffect(arguments) {
            viewModel.sendAction(EditLabelStore.Action.Init(noteIds))
        }

        viewModel.event.CollectAsEvent { event ->
            when (event) {
                is EditLabelStore.Event.Navigation -> viewModel.processNavigationEvent(event)
                EditLabelStore.Event.SnackbarError -> {
                    //TODO()
                }
            }
        }

        val screenState by remember {
            viewModel.state
        }.collectAsState()

        EditLabelScreen(
            modifier = modifier,
            state = screenState,
            processAction = viewModel::sendAction
        )
    }
}