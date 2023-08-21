package com.stslex93.notes.feature.edit.domain.interactor

import com.stslex93.notes.feature.edit.domain.model.NoteDomainModel
import kotlinx.coroutines.flow.Flow

interface NoteEditInteractor {

    fun getNote(id: Int): Flow<NoteDomainModel>

    suspend fun insert(note: NoteDomainModel)
}

