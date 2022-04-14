package com.stslex93.notes.domain.mappers

import com.stslex93.core.Mapper
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.model.NoteDomainModel
import javax.inject.Inject

interface NoteDomainDataMapper : Mapper.Data<NoteDomainModel, NoteDataModel> {

    class Base @Inject constructor() : NoteDomainDataMapper {

        override fun map(data: NoteDomainModel): NoteDataModel = with(data) {
            NoteDataModel.Base(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}