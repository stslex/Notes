package com.stslex.notes.domain.interactor.impl

import com.stslex.notes.data.model.NoteData
import com.stslex.notes.domain.interactor.interf.NoteUpdateSingleInteractor
import com.stslex.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteUpdateSingleInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : NoteUpdateSingleInteractor {

    override suspend fun invoke(note: NoteData) = repository.update(note)
}