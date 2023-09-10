package com.stslex93.notes.feature.edit_label.domain.model

import com.stslex93.notes.core.label.model.LabelDataModel
import com.stslex93.notes.core.notes.model.NoteDataModel

fun NoteDataModel.toDomain(labels: Set<LabelDomain>) = NoteDomain(
    id = id,
    title = title,
    content = content,
    timestamp = timestamp,
    labels = labels
)

fun LabelDataModel.toDomain(): LabelDomain = LabelDomain(
    uuid = uuid,
    title = title,
)