package com.stslex93.notes.domain.interactor.interf

import com.stslex93.notes.domain.model.NoteDomainModel

fun interface NoteInsertAllInteractor {
    suspend fun invoke(notes: List<NoteDomainModel>)
}