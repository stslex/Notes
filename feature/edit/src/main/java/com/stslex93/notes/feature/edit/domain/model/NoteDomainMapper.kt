package com.stslex93.notes.feature.edit.domain.model

import com.stslex93.notes.core.notes.model.NoteDataModel

fun NoteDataModel.toDomain() = NoteDomainModel(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp
)

fun NoteDomainModel.toData() = NoteDataModel(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp
)