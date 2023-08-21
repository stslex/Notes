package com.stslex93.notes.domain.interactor.impl

import com.stslex93.notes.domain.interactor.interf.NoteEditInteractor
import com.stslex93.notes.domain.mappers.toData
import com.stslex93.notes.domain.mappers.toDomain
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteEditInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
) : NoteEditInteractor {

    private val _note = MutableStateFlow(NoteDomainModel.EMPTY)
    override val note: StateFlow<NoteDomainModel>
        get() = _note.asStateFlow()

    override suspend fun getNote(id: Int) {
        if (id == -1) {
            insert(NoteDomainModel.EMPTY)
            repository.getLastNote()
        } else {
            repository.getNote(id)
        }.map {
            it.toDomain()
        }.collect {
            _note.emit(it)
        }
    }

    override suspend fun insert(note: NoteDomainModel) {
        repository.insert(note.toData())
    }
}