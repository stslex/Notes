package com.stslex93.notes.ui.model

import com.stslex93.notes.domain.model.NoteDomainModel

fun NoteDomainModel.toUI() = NoteUIModel(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp
)

fun NoteUIModel.toDomain() = NoteDomainModel(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp
)