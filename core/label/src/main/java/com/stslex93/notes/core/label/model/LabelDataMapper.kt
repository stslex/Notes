package com.stslex93.notes.core.label.model

import com.stslex93.notes.core.database.label.LabelEntity
import java.time.OffsetDateTime

object LabelDataMapper {

    fun LabelEntity.toData(): LabelDataModel = LabelDataModel(
        uuid = uuid,
        title = title,
        timestamp = OffsetDateTime.parse(timestamp)
    )

    fun LabelDataModel.toEntity(): LabelEntity = LabelEntity(
        uuid = uuid,
        title = title,
        timestamp = timestamp.toString()
    )
}