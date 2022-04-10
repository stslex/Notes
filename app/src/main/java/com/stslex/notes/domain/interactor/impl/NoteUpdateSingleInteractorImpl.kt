package com.stslex.notes.domain.interactor.impl

import com.stslex.notes.domain.interactor.interf.NoteUpdateSingleInteractor
import com.stslex.notes.domain.mappers.NoteDomainDataMapper
import com.stslex.notes.domain.model.NoteDomainModel
import com.stslex.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteUpdateSingleInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
    private val mapper: NoteDomainDataMapper
) : NoteUpdateSingleInteractor {

    override suspend fun invoke(note: NoteDomainModel) {
        repository.update(mapper.map(note))
    }
}