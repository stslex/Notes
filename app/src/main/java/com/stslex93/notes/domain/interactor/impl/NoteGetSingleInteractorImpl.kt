package com.stslex93.notes.domain.interactor.impl

import com.stslex93.notes.domain.interactor.interf.NoteGetSingleInteractor
import com.stslex93.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteGetSingleInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : NoteGetSingleInteractor {

    override fun invoke(id: Int) = repository.getNote(id)
}