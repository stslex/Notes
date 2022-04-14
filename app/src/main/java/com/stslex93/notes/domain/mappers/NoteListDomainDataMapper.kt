package com.stslex93.notes.domain.mappers

import com.stslex93.core.Mapper
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.model.NoteDomainModel
import javax.inject.Inject

interface NoteListDomainDataMapper : Mapper.Data<List<NoteDomainModel>, List<NoteDataModel>> {

    class Base @Inject constructor(
        private val mapper: NoteDomainDataMapper
    ) : NoteListDomainDataMapper {

        override fun map(data: List<NoteDomainModel>): List<NoteDataModel> = data.map(mapper::map)
    }
}