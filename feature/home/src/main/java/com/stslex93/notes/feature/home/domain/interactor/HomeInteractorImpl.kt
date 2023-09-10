package com.stslex93.notes.feature.home.domain.interactor

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex93.notes.core.label.repository.LabelRepository
import com.stslex93.notes.core.notes.repository.NoteRepository
import com.stslex93.notes.feature.home.domain.model.NoteDomain
import com.stslex93.notes.feature.home.domain.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeInteractorImpl @Inject constructor(
    private val noteRepository: NoteRepository,
    private val labelRepository: LabelRepository
) : HomeInteractor {

    override fun searchNotes(query: String): Flow<PagingData<NoteDomain>> =
        noteRepository
            .searchNotes(query)
            .map { pagingData ->
                pagingData.map { noteDataModel ->
                    val labels = labelRepository
                        .getAllLabels(noteDataModel.labelUuids)
                        .map {
                            it.toDomain()
                        }
                        .toSet()
                    noteDataModel.toDomain(labels)
                }
            }
            .flowOn(Dispatchers.IO)

    override suspend fun deleteNotes(noteIds: List<Int>) {
        noteRepository.deleteNotesById(noteIds)
    }
}