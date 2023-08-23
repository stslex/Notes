package com.stslex93.notes.feature.edit.ui.init

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.stslex93.notes.feature.edit.ui.EditNoteViewModel
import com.stslex93.notes.feature.edit.ui.EditScreen
import com.stslex93.notes.feature.edit.ui.store.EditStore.Action

@Composable
fun InitEditScreen(
    viewModel: EditNoteViewModel,
    modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) {
                viewModel.sendAction(Action.SaveNote)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val state by remember { viewModel.state }.collectAsState()

    EditScreen(
        modifier = modifier,
        state = state,
        onInputTitle = { viewModel.sendAction(Action.InputTitle(it)) },
        onInputContent = { viewModel.sendAction(Action.InputContent(it)) },
        onBackButtonClicked = viewModel::popBackStack
    )
}