package com.stslex93.notes.ui.model.mapper

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.ui.model.NoteUIModel

class NoteUIDomainMapper : Mapper.Data<NoteUIModel, NoteDomainModel> {

    override fun map(data: NoteUIModel): NoteDomainModel = with(data) {
        NoteDomainModel(
            id = id,
            title = title,
            content = content,
            timestamp = timestamp
        )
    }
}