package com.stslex93.notes.domain.mappers

import com.stslex93.core.Mapper
import com.stslex93.core.Resource
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.model.NoteDomainModel
import javax.inject.Inject

interface NoteListDataDomainMapper :
    Mapper.DataToUI<List<NoteDataModel>, Resource<List<NoteDomainModel>>> {

    class Base @Inject constructor(
        private val mapper: NoteDataDomainPrimaryMapper
    ) : NoteListDataDomainMapper {

        override fun map(): Resource<List<NoteDomainModel>> = Resource.Loading

        override fun map(exception: Exception): Resource<List<NoteDomainModel>> =
            Resource.Failure(exception)

        override fun map(data: List<NoteDataModel>): Resource<List<NoteDomainModel>> =
            Resource.Success(data.map(mapper::map))
    }
}