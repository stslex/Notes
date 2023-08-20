package com.stslex93.notes.domain.mappers

import com.stslex93.notes.core.Mapper
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.model.NoteDomainModel

class NoteDomainDataMapper : Mapper.Data<NoteDomainModel, NoteDataModel> {

    override fun map(data: NoteDomainModel): NoteDataModel = with(data) {
        NoteDataModel.Base(
            id = id(),
            title = title(),
            content = content(),
            timestamp = timestamp()
        )
    }
}