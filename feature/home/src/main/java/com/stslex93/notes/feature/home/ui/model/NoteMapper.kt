package com.stslex93.notes.feature.home.ui.model

import com.stslex93.notes.feature.home.domain.model.NoteDomain

fun NoteDomain.toUI() = Note(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp,
)

fun Note.toDomain() = NoteDomain(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp,
)