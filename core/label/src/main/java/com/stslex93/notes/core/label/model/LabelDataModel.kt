package com.stslex93.notes.core.label.model

import android.graphics.Color
import androidx.core.graphics.toColor
import java.time.OffsetDateTime
import java.util.UUID

data class LabelDataModel(
    val uuid: String = UUID.randomUUID().toString(),
    val title: String,
    val colorRgb: Int = Color.GRAY.toColor().toArgb(),
    val timestamp: OffsetDateTime = OffsetDateTime.now()
)