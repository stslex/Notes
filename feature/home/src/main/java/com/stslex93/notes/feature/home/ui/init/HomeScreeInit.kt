package com.stslex93.notes.feature.home.ui.init

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex93.notes.core.ui.CollectAsEvent
import com.stslex93.notes.feature.home.ui.HomeScreen
import com.stslex93.notes.feature.home.ui.HomeViewModel
import com.stslex93.notes.feature.home.ui.store.HomeStore

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
    HomeScreen(
        modifier = modifier
    )
}