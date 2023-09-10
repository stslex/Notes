package com.stslex93.notes.core.label.model

import java.time.OffsetDateTime
import java.util.UUID

data class LabelDataModel(
    val uuid: String = UUID.randomUUID().toString(),
    val title: String,
    val timestamp: OffsetDateTime = OffsetDateTime.now()
)