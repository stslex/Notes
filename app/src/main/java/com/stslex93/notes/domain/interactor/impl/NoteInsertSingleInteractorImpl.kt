package com.stslex93.notes.domain.interactor.impl

import com.stslex93.notes.domain.interactor.interf.NoteInsertSingleInteractor
import com.stslex93.notes.domain.mappers.NoteDomainDataMapper
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteInsertSingleInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: NoteDomainDataMapper
) : NoteInsertSingleInteractor {

    override suspend fun invoke(note: NoteDomainModel) {
        repository.insert(mapper.map(note))
    }
}