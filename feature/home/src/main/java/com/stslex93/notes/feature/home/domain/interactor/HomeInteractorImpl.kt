package com.stslex93.notes.feature.home.domain.interactor

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex93.notes.core.notes.repository.NoteRepository
import com.stslex93.notes.feature.home.domain.model.NoteDomain
import com.stslex93.notes.feature.home.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeInteractorImpl @Inject constructor(
    private val repository: NoteRepository
) : HomeInteractor {

    override fun queryNotes(query: String): Flow<PagingData<NoteDomain>> =
        repository.searchNotes(query).map { pagingData ->
            pagingData.map { note ->
                note.toDomain()
            }
        }

    override suspend fun deleteNotes(noteIds: List<Int>) {
        repository.deleteNotesById(noteIds)
    }
}