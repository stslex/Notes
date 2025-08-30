package com.stslex93.notes.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.toImmutableSet
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

object Extensions {

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

    fun <T> ImmutableSet<T>.removeItem(
        item: T
    ): ImmutableSet<T> = this.toMutableSet()
        .apply { remove(item) }
        .toImmutableSet()

    fun <T> ImmutableSet<T>.addItem(
        item: T
    ): ImmutableSet<T> = this.toMutableSet()
        .apply {
            add(item)
        }
        .toImmutableSet()
}

