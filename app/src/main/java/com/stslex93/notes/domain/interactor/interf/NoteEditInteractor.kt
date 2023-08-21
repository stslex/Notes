package com.stslex93.notes.domain.interactor.interf

import com.stslex93.notes.domain.model.NoteDomainModel
import kotlinx.coroutines.flow.Flow

interface NoteEditInteractor {

    fun getNote(id: Int): Flow<NoteDomainModel>

    suspend fun insert(note: NoteDomainModel)
}

