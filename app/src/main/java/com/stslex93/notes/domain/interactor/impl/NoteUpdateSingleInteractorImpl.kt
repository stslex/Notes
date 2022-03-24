package com.stslex93.notes.domain.interactor.impl

import com.stslex93.notes.data.model.NoteData
import com.stslex93.notes.domain.interactor.interf.NoteUpdateSingleInteractor
import com.stslex93.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteUpdateSingleInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : NoteUpdateSingleInteractor {
    override suspend fun invoke(note: NoteData) = repository.update(note)
}