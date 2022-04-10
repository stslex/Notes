package com.stslex.notes.domain.mappers

import com.stslex.core.Mapper
import com.stslex.core.Resource
import com.stslex.notes.data.model.NoteDataModel
import com.stslex.notes.domain.model.NoteDomainModel
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