package com.stslex.notes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stslex.notes.domain.interactor.interf.NoteDeleteByIdsInteractor
import com.stslex.notes.domain.interactor.interf.NoteGetAllWithQueryInteractor
import com.stslex.notes.domain.interactor.interf.NoteInsertAllInteractor
import com.stslex.notes.ui.mapper.NotePagingDomainUIMapper
import com.stslex.notes.ui.mapper.NoteUIDomainMapper
import com.stslex.notes.ui.model.NoteUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val noteGetAllInteractor: NoteGetAllWithQueryInteractor,
    private val noteDeleteByIdsInteractor: NoteDeleteByIdsInteractor,
    private val insertAllInteractor: NoteInsertAllInteractor,
    private val pagingMapper: NotePagingDomainUIMapper,
    private val mapperUIDomain: NoteUIDomainMapper
) : ViewModel() {

    @ExperimentalCoroutinesApi
    val notes: StateFlow<PagingData<NoteUIModel>> = noteGetAllInteractor.invoke()
        .mapLatest(pagingMapper::map)
        .flowOn(Dispatchers.IO)
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PagingData.empty()
        )

    fun setQuery(query: String) {
        noteGetAllInteractor.setQuery(query)
    }

    fun deleteNotesByIds(ids: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDeleteByIdsInteractor.invoke(ids)
        }
    }

    fun insertAll(notes: List<NoteUIModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertAllInteractor.invoke(notes.map(mapperUIDomain::map))
        }
    }
}