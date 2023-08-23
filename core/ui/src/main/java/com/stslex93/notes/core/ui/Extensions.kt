package com.stslex93.notes.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun <T> SharedFlow<T>.CollectAsEvent(
    minActionState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (T) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        this@CollectAsEvent
            .flowWithLifecycle(
                lifecycle = lifecycleOwner.lifecycle,
                minActiveState = minActionState
            )
            .onEach(action)
            .launchIn(lifecycleOwner.lifecycleScope)
    }
}