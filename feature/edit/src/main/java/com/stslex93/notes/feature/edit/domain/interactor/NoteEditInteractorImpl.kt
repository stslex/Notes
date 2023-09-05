package com.stslex93.notes.feature.edit.domain.interactor

import com.stslex93.notes.core.notes.repository.NoteRepository
import com.stslex93.notes.feature.edit.domain.model.NoteDomainModel
import com.stslex93.notes.feature.edit.domain.model.toData
import com.stslex93.notes.feature.edit.domain.model.toDomain
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteEditInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
) : NoteEditInteractor {

    override fun getNote(id: Int) = repository
        .getNoteFlow(id).map {
            it.toDomain()
        }

    override suspend fun insert(note: NoteDomainModel) {
        repository.insert(note.toData())
    }
}
