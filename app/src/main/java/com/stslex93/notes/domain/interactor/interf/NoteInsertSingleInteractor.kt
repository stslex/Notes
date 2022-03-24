package com.stslex93.notes.domain.interactor.interf

import com.stslex93.notes.data.model.NoteData

interface NoteInsertSingleInteractor {
    suspend fun invoke(note: NoteData)
}