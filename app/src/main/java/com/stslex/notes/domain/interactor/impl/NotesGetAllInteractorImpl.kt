package com.stslex.notes.domain.interactor.impl

import com.stslex.core.Resource
import com.stslex.notes.domain.interactor.interf.NoteGetAllInteractor
import com.stslex.notes.domain.mappers.NoteListDataDomainMapper
import com.stslex.notes.domain.model.NoteDomainModel
import com.stslex.notes.domain.repository.NoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class NotesGetAllInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: NoteListDataDomainMapper
) : NoteGetAllInteractor {

    @ExperimentalCoroutinesApi
    override fun invoke(): Flow<Resource<List<NoteDomainModel>>> =
        repository.getAll().flatMapLatest {
            flowOf(it.map(mapper))
        }
}