package com.stslex93.notes.feature.edit.ui.model

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableSet

@Stable
data class Note(
    val uuid: Int,
    val title: String,
    val content: String,
    val timestamp: Long,
    val labels: ImmutableSet<Label>
)
