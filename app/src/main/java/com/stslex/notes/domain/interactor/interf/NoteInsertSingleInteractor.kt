package com.stslex.notes.domain.interactor.interf

import com.stslex.notes.data.model.NoteData

interface NoteInsertSingleInteractor {
    suspend fun invoke(note: NoteData)
}