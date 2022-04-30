package com.stslex93.notes.domain.interactor.impl

import com.stslex93.core.Mapper
import com.stslex93.core.Resource
import com.stslex93.notes.data.model.NoteDataModel
import com.stslex93.notes.domain.interactor.interf.NoteGetSingleInteractor
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.domain.repository.NoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class NoteGetSingleInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: Mapper.DataToUI<NoteDataModel, Resource<NoteDomainModel>>
) : NoteGetSingleInteractor {

    @ExperimentalCoroutinesApi
    override fun invoke(id: Int): Flow<Resource<NoteDomainModel>> =
        repository.getNote(id).flatMapLatest {
            flowOf(it.map(mapper))
        }
}