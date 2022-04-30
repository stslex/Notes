package com.stslex93.notes.domain.mappers

import com.stslex93.core.Mapper
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.model.NoteDomainModel

class NoteDataDomainPrimaryMapper : Mapper.Data<NoteDataModel, NoteDomainModel> {

    override fun map(data: NoteDataModel): NoteDomainModel = with(data) {
        NoteDomainModel.Base(
            id = id(),
            title = title(),
            content = content(),
            timestamp = timestamp()
        )
    }
}