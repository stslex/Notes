package com.stslex.notes.domain.interactor.interf

import com.stslex.notes.domain.model.NoteDomainModel

interface NoteUpdateSingleInteractor {
    suspend fun invoke(note: NoteDomainModel)
}