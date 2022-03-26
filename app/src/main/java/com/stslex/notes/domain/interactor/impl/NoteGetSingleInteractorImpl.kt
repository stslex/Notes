package com.stslex.notes.domain.interactor.impl

import com.stslex.notes.domain.interactor.interf.NoteGetSingleInteractor
import com.stslex.notes.domain.repository.NoteRepository
import javax.inject.Inject

class NoteGetSingleInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : NoteGetSingleInteractor {

    override fun invoke(id: Int) = repository.getNote(id)
}