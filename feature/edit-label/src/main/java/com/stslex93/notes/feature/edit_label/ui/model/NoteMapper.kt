package com.stslex93.notes.feature.edit_label.ui.model

import com.stslex93.notes.core.label.model.LabelDataModel
import com.stslex93.notes.feature.edit_label.domain.model.LabelDomain
import java.time.OffsetDateTime

fun LabelDomain.toUi() = Label(
    uuid = uuid,
    title = title,
)

fun LabelDomain.toData() = LabelDataModel(
    uuid = uuid,
    title = title,
    timestamp = OffsetDateTime.now()
)