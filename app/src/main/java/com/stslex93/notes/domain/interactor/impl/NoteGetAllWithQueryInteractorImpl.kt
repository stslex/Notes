package com.stslex93.notes.domain.interactor.impl

import androidx.paging.PagingData
import com.stslex93.notes.data.repository.SearchNoteRepository
import com.stslex93.notes.domain.interactor.interf.NoteGetAllWithQueryInteractor
import com.stslex93.notes.domain.mappers.NotePagingDataDomainMapper
import com.stslex93.notes.domain.model.NoteDomainModel
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