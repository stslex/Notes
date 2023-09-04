package com.stslex93.notes.feature.home.ui.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.stslex93.notes.feature.home.domain.model.LabelDomain
import com.stslex93.notes.feature.home.domain.model.NoteDomain
import kotlinx.collections.immutable.toImmutableSet

fun NoteDomain.toUI() = Note(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp,
    labels = labels.map { it.toUi() }.toImmutableSet()
)

fun Note.toDomain() = NoteDomain(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp,
    labels = labels.map { it.toDomain() }.toSet()
)

fun LabelDomain.toUi() = Label(
    uuid = uuid,
    title = title,
    color = Color(colorRgb)
)

fun Label.toDomain() = LabelDomain(
    uuid = uuid,
    title = title,
    colorRgb = color.toArgb()
)