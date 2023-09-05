package com.stslex93.notes.feature.edit_label.domain.interactor

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex93.notes.core.label.repository.LabelRepository
import com.stslex93.notes.core.notes.repository.NoteRepository
import com.stslex93.notes.feature.edit_label.domain.model.LabelDomain
import com.stslex93.notes.feature.edit_label.domain.model.toDomain
import com.stslex93.notes.feature.edit_label.ui.model.toData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EditLabelInteractorImpl @Inject constructor(
    private val noteRepository: NoteRepository,
    private val labelRepository: LabelRepository
) : EditLabelInteractor {

    override fun searchLabels(
        query: String
    ): Flow<PagingData<LabelDomain>> = labelRepository
        .searchLabels(query)
        .map { pagingData ->
            pagingData.map { it.toDomain() }
        }

    override suspend fun createLabelAndAdd(noteIds: Set<Int>, label: LabelDomain) {
        labelRepository.addLabel(label.toData())
        noteIds.forEach { id ->
            val note = noteRepository.getNote(id)
            val newNote = note.copy(
                labelUuids = note.labelUuids.toMutableSet().apply {
                    add(label.uuid)
                }
            )
            noteRepository.insert(newNote)
        }
    }

    override suspend fun addLabel(noteIds: Set<Int>, labelUuid: String) {
        noteIds.forEach { id ->
            val note = noteRepository.getNote(id)
            val newNote = note.copy(
                labelUuids = note.labelUuids.toMutableSet().apply {
                    add(labelUuid)
                }
            )
            noteRepository.insert(newNote)
        }
    }
}