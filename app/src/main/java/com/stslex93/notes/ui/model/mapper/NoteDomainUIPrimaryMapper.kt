package com.stslex93.notes.ui.model.mapper

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.ui.model.NoteUIModel

class NoteDomainUIPrimaryMapper : Mapper.Data<NoteDomainModel, NoteUIModel> {

    override fun map(data: NoteDomainModel): NoteUIModel = with(data) {
        NoteUIModel(
            id = id,
            title = title,
            content = content,
            timestamp = timestamp
        )
    }
}