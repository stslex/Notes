package com.stslex93.notes.feature.edit.ui.model

import com.stslex93.notes.feature.edit.domain.model.LabelDomain
import com.stslex93.notes.feature.edit.domain.model.NoteDomainModel
import kotlinx.collections.immutable.toImmutableSet

fun NoteDomainModel.toPresentation(): Note = Note(
    uuid = id,
    title = title,
    content = content,
    timestamp = timestamp,
    labels = labels.map { it.toPresentation() }.toImmutableSet()
)

fun Note.toDomain(): NoteDomainModel = NoteDomainModel(
    id = uuid,
    title = title,
    content = content,
    timestamp = timestamp,
    labels = labels.map { it.toDomain() }.toSet()
)

fun LabelDomain.toPresentation() = Label(uuid, title)

fun Label.toDomain() = LabelDomain(uuid, title)
