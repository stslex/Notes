package com.stslex93.notes.feature.home.domain.model

import com.stslex93.notes.core.notes.model.NoteDataModel

fun NoteDataModel.toDomain() = NoteDomain(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp,
)