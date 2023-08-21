package com.stslex93.notes.data.model

import com.stslex93.notes.data.entity.NoteEntity

fun NoteDataModel.toEntity() = NoteEntity(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp
)

fun NoteEntity.toData() = NoteDataModel(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp
)