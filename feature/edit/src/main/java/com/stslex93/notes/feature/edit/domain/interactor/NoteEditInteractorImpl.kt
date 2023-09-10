package com.stslex93.notes.feature.edit.domain.interactor

import com.stslex93.notes.core.label.repository.LabelRepository
import com.stslex93.notes.core.notes.repository.NoteRepository
import com.stslex93.notes.feature.edit.domain.model.NoteDomainModel
import com.stslex93.notes.feature.edit.domain.model.toData
import com.stslex93.notes.feature.edit.domain.model.toDomain
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteEditInteractorImpl @Inject constructor(
    private val repository: NoteRepository,
    private val labelRepository: LabelRepository
) : NoteEditInteractor {

    override fun getNote(id: Int) = repository
        .getNoteFlow(id)
        .map { note ->
            val labels = labelRepository
                .getAllLabels(note.labelUuids)
                .toDomain()
            note.toDomain(
                labels = labels
            )
        }

    override suspend fun insert(note: NoteDomainModel) {
        repository.insert(note.toData())
    }
}
