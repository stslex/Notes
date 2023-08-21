package com.stslex93.notes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stslex93.notes.core.Mapper
import com.stslex93.notes.domain.interactor.interf.NoteDeleteByIdsInteractor
import com.stslex93.notes.domain.interactor.interf.NoteGetAllWithQueryInteractor
import com.stslex93.notes.domain.interactor.interf.NoteInsertAllInteractor
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.ui.model.NoteUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val noteGetAllInteractor: NoteGetAllWithQueryInteractor,
    private val noteDeleteByIdsInteractor: NoteDeleteByIdsInteractor,
    private val insertAllInteractor: NoteInsertAllInteractor,
    private val pagingMapper: Mapper.Data<PagingData<NoteDomainModel>, PagingData<NoteUIModel>>,
    private val mapperUIDomain: Mapper.Data<NoteUIModel, NoteDomainModel>
) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
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

    fun deleteNotesByIds(notes: List<NoteUIModel>) {
        val noteList: List<Int> = notes.map { it.id };
        viewModelScope.launch(Dispatchers.IO) {
            noteDeleteByIdsInteractor.invoke(noteList)
        }
    }

    fun insertAll(notes: List<NoteUIModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            insertAllInteractor.invoke(notes.map(mapperUIDomain::map))
        }
    }
}