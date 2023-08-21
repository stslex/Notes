package com.stslex93.notes.ui.edit.model

import com.stslex93.notes.domain.model.NoteDomainModel

fun NoteDomainModel.toPresentation(): Note = Note(
    uuid = id,
    title = title,
    content = content,
    timestamp = timestamp
)

fun Note.toDomain(): NoteDomainModel = NoteDomainModel(
    id = uuid,
    title = title,
    content = content,
    timestamp = timestamp
)