package com.stslex93.notes.domain.model

import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.model.NoteDomainModel

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