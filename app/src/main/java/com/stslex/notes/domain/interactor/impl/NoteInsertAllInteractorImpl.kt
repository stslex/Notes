package com.stslex.notes.domain.interactor.impl

import com.stslex.notes.domain.interactor.interf.NoteInsertAllInteractor
import com.stslex.notes.domain.mappers.NoteListDomainDataMapper
import com.stslex.notes.domain.model.NoteDomainModel
import com.stslex.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteInsertAllInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: NoteListDomainDataMapper
) : NoteInsertAllInteractor {

    override suspend fun invoke(notes: List<NoteDomainModel>) {
        repository.insertAll(mapper.map(notes))
    }
}