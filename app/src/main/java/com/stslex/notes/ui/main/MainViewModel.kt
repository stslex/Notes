package com.stslex.notes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.notes.core.Resource
import com.stslex.notes.data.model.NoteListDataUIMapper
import com.stslex.notes.domain.interactor.interf.NoteDeleteByIdsInteractor
import com.stslex.notes.domain.interactor.interf.NoteGetAllInteractor
import com.stslex.notes.ui.model.NoteUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val noteGetAllInteractor: NoteGetAllInteractor,
    private val noteDeleteByIdsInteractor: NoteDeleteByIdsInteractor,
    private val listMapper: NoteListDataUIMapper
) : ViewModel() {

    @ExperimentalCoroutinesApi
    suspend fun getAllNotes(): StateFlow<Resource<List<NoteUI>>> = noteGetAllInteractor.invoke()
        .flatMapLatest { flowOf(it.map(listMapper)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Resource.Loading
        )

    suspend fun deleteNotesByIds(ids: List<Int>) = viewModelScope.launch(Dispatchers.IO) {
        noteDeleteByIdsInteractor.invoke(ids)
    }
}