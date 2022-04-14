package com.stslex.notes.domain.interactor.interf

import com.stslex.notes.domain.model.NoteDomainModel

fun interface NoteInsertSingleInteractor {
    suspend fun invoke(note: NoteDomainModel)
}