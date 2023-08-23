package com.stslex93.notes.feature.home.ui.model

import androidx.compose.runtime.Stable

@Stable
data class Note(
    val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long,
)