package com.stslex93.notes.domain.mappers

import com.stslex93.core.Mapper
import com.stslex93.core.Resource
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.model.NoteDomainModel
import javax.inject.Inject

interface NoteDataDomainMapper : Mapper.DataToUI<NoteDataModel, Resource<NoteDomainModel>> {

    class Base @Inject constructor(
        private val mapper: NoteDataDomainPrimaryMapper
    ) : NoteDataDomainMapper {

        override fun map(data: NoteDataModel): Resource<NoteDomainModel> =
            Resource.Success(mapper.map(data))

        override fun map(): Resource<NoteDomainModel> = Resource.Loading

        override fun map(exception: Exception): Resource<NoteDomainModel> =
            Resource.Failure(exception)
    }
}