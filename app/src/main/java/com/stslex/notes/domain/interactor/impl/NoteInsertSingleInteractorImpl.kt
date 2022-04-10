package com.stslex.notes.domain.interactor.impl

import com.stslex.notes.domain.interactor.interf.NoteInsertSingleInteractor
import com.stslex.notes.domain.mappers.NoteDomainDataMapper
import com.stslex.notes.domain.model.NoteDomainModel
import com.stslex.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteInsertSingleInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: NoteDomainDataMapper
) : NoteInsertSingleInteractor {

    override suspend fun invoke(note: NoteDomainModel) {
        repository.insert(mapper.map(note))
    }
}