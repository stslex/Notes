package com.stslex93.notes.feature.home.ui.model

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Stable
data class Label(
    val uuid: String,
    val title: String,
    val color: Color
)
