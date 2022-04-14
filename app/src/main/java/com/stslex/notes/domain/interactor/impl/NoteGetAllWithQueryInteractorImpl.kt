package com.stslex.notes.domain.interactor.impl

import androidx.paging.PagingData
import com.stslex.notes.data.repository.SearchNoteRepository
import com.stslex.notes.domain.interactor.interf.NoteGetAllWithQueryInteractor
import com.stslex.notes.domain.mappers.NotePagingDataDomainMapper
import com.stslex.notes.domain.model.NoteDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class NoteGetAllWithQueryInteractorImpl @Inject constructor(
    private val repository: SearchNoteRepository,
    private val mapper: NotePagingDataDomainMapper
) : NoteGetAllWithQueryInteractor {

    @ExperimentalCoroutinesApi
    override fun invoke(): Flow<PagingData<NoteDomainModel>> =
        repository.notes.mapLatest(mapper::map)

    override fun setQuery(query: String) {
        repository.setQuery(query)
    }
}