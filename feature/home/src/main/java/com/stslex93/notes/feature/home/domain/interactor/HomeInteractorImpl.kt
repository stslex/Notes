package com.stslex93.notes.feature.home.domain.interactor

import androidx.paging.PagingData
import com.stslex93.notes.core.notes.model.NoteDataModel
import com.stslex93.notes.core.notes.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class HomeInteractorImpl(
    private val repository: NoteRepository
) : HomeInteractor {

    override val queryNotes: Flow<PagingData<NoteDataModel>>
        get() = repository.searchNotes

    override fun setQuery(query: String) {
        repository.search(query)
    }

    override suspend fun deleteNote(noteIds: List<Int>) {
        repository.deleteNotesById(noteIds)
    }
}