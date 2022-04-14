package com.stslex.notes.domain.interactor.impl

import com.stslex.notes.domain.interactor.interf.NoteInsertAllInteractor
import com.stslex.notes.domain.mappers.NoteDomainDataMapper
import com.stslex.notes.domain.model.NoteDomainModel
import com.stslex.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteInsertAllInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: NoteDomainDataMapper
) : NoteInsertAllInteractor {

    override suspend fun invoke(notes: List<NoteDomainModel>) {
        repository.insertAll(notes.map(mapper::map))
    }
}