package com.stslex93.notes.domain.interactor

import androidx.paging.PagingData
import androidx.paging.map
import com.stslex93.notes.core.notes.repository.NoteRepository
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.domain.model.toData
import com.stslex93.notes.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MainScreenInteractorImpl(
    private val repository: NoteRepository
) : MainScreenInteractor {

    override val searchNotes: Flow<PagingData<NoteDomainModel>>
        get() = repository.searchNotes
            .map { pagingData ->
                pagingData.map { it.toDomain() }
            }

    override fun search(query: String) {
        repository.search(query)
    }

    override suspend fun insertAll(notes: List<NoteDomainModel>) {
        repository.insertAll(notes.map { it.toData() })
    }

    override suspend fun deleteAll(notes: List<Int>) {
        repository.deleteNotesById(notes)
    }
}