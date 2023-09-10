package com.stslex93.notes.feature.edit.domain.model

import com.stslex93.notes.core.label.model.LabelDataModel
import com.stslex93.notes.core.notes.model.NoteDataModel

fun NoteDataModel.toDomain(
    labels: Set<LabelDomain>
) = NoteDomainModel(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp,
    labels = labels
)

fun NoteDomainModel.toData() = NoteDataModel(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp,
    labelUuids = labels.map { it.uuid }.toSet()
)

fun Set<LabelDataModel>.toDomain() = map { label ->
    label.toDomain()
}.toSet()

fun LabelDataModel.toDomain() = LabelDomain(
    uuid = uuid,
    title = title
)