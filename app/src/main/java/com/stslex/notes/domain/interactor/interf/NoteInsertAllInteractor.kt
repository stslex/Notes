package com.stslex.notes.domain.interactor.interf

import com.stslex.notes.domain.model.NoteDomainModel

fun interface NoteInsertAllInteractor {
    suspend fun invoke(notes: List<NoteDomainModel>)
}