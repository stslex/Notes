package com.stslex93.notes.core.notes.model

import com.stslex93.notes.core.database.note.NoteEntity

fun NoteDataModel.toEntity() = NoteEntity(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp,
    labels = emptyList()
)

fun NoteEntity.toData() = NoteDataModel(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp
)