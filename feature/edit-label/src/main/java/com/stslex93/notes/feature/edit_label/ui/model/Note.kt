package com.stslex93.notes.feature.edit_label.ui.model

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableSet

@Stable
data class Note(
    val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long,
    val labels: ImmutableSet<Label>
)