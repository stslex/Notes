package com.stslex93.notes.ui.mapper

import com.stslex93.core.Mapper
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.ui.model.NoteUIModel

class NoteUIDomainMapper : Mapper.Data<NoteUIModel, NoteDomainModel> {

    override fun map(data: NoteUIModel): NoteDomainModel = with(data) {
        NoteDomainModel.Base(
            id = id(),
            title = title(),
            content = content(),
            timestamp = timestamp()
        )
    }
}