package com.stslex93.notes.feature.home.ui.init

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.stslex93.notes.core.ui.Extensions.CollectAsEvent
import com.stslex93.notes.feature.home.ui.HomeScreen
import com.stslex93.notes.feature.home.ui.HomeViewModel
import com.stslex93.notes.feature.home.ui.store.HomeStore
import com.stslex93.notes.feature.home.ui.store.HomeStore.Action

@Composable
fun HomeScreeInit(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    viewModel.event.CollectAsEvent { event ->
        when (event) {
            is HomeStore.Event.Navigation -> viewModel.processNavigation(event)
        }
    }

    val state by remember {
        viewModel.state
    }.collectAsState()

    val notes = remember {
        state.notes()
    }.collectAsLazyPagingItems()

    BackHandler(
        enabled = state.selectedNotes.isNotEmpty() || state.query.isNotEmpty()
    ) {
        viewModel.sendAction(Action.ClearSelection)
    }

    HomeScreen(
        modifier = modifier,
        notes = notes,
        selectedNotes = state.selectedNotes,
        query = state.query,
        sendAction = viewModel::sendAction
    )
}