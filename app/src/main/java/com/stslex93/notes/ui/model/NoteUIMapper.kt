package com.stslex93.notes.ui.model

import com.stslex93.notes.core.Resource
import com.stslex93.notes.domain.model.NoteDomainModel

fun Resource<NoteDomainModel>.toUI(): Resource<NoteUIModel> = when (this) {
    is Resource.Failure -> Resource.Failure(exception)
    Resource.Loading -> Resource.Loading
    is Resource.Success -> Resource.Success(data.toUI())
}

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