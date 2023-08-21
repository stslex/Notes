package com.stslex93.notes.domain.interactor.impl

import com.stslex93.notes.domain.interactor.interf.NoteEditInteractor
import com.stslex93.notes.domain.mappers.toData
import com.stslex93.notes.domain.mappers.toDomain
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteEditInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
) : NoteEditInteractor {

    override fun getNote(id: Int) = repository
        .getNote(id).map {
            it.toDomain()
        }

    override suspend fun insert(note: NoteDomainModel) {
        repository.insert(note.toData())
    }
}