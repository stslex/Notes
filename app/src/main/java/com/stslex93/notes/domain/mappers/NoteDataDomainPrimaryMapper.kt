package com.stslex93.notes.domain.mappers

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.model.NoteDomainModel

class NoteDataDomainPrimaryMapper : Mapper.Data<NoteDataModel, NoteDomainModel> {

    override fun map(data: NoteDataModel): NoteDomainModel = with(data) {
        NoteDomainModel(
            id = id,
            title = title,
            content = content,
            timestamp = timestamp
        )
    }
}