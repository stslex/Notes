package com.stslex93.notes.domain.mappers

import com.stslex93.core.Mapper
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.model.NoteDomainModel
import javax.inject.Inject

interface NoteDataDomainPrimaryMapper : Mapper.Data<NoteDataModel, NoteDomainModel> {

    class Base @Inject constructor() : NoteDataDomainPrimaryMapper {

        override fun map(data: NoteDataModel): NoteDomainModel = with(data) {
            NoteDomainModel.Base(
                id = id(),
                title = title(),
                content = content(),
                timestamp = timestamp()
            )
        }
    }
}