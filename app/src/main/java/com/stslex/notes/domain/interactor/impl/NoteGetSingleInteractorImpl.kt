package com.stslex.notes.domain.interactor.impl

import com.stslex.core.Resource
import com.stslex.notes.domain.interactor.interf.NoteGetSingleInteractor
import com.stslex.notes.domain.mappers.NoteDataDomainMapper
import com.stslex.notes.domain.model.NoteDomainModel
import com.stslex.notes.domain.repository.NoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class NoteGetSingleInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: NoteDataDomainMapper
) : NoteGetSingleInteractor {

    @ExperimentalCoroutinesApi
    override fun invoke(id: Int): Flow<Resource<NoteDomainModel>> =
        repository.getNote(id).flatMapLatest {
            flowOf(it.map(mapper))
        }
}