package com.stslex93.notes.domain.interactor.impl

import com.stslex93.notes.domain.interactor.interf.NoteInsertAllInteractor
import com.stslex93.notes.domain.mappers.NoteDomainDataMapper
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteInsertAllInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: NoteDomainDataMapper
) : NoteInsertAllInteractor {

    override suspend fun invoke(notes: List<NoteDomainModel>) {
        repository.insertAll(notes.map(mapper::map))
    }
}