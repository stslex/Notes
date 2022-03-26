package com.stslex.notes.domain.interactor.interf

import com.stslex.notes.data.model.NoteData

interface NoteUpdateSingleInteractor {
    suspend fun invoke(note: NoteData)
}