package com.stslex.notes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core.Resource
import com.stslex.notes.domain.interactor.interf.NoteDeleteByIdsInteractor
import com.stslex.notes.domain.interactor.interf.NoteGetAllInteractor
import com.stslex.notes.ui.mapper.NoteListDomainUIMapper
import com.stslex.notes.ui.model.NoteUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val noteGetAllInteractor: NoteGetAllInteractor,
    private val noteDeleteByIdsInteractor: NoteDeleteByIdsInteractor,
    private val listMapper: NoteListDomainUIMapper
) : ViewModel() {

    @ExperimentalCoroutinesApi
    fun getAllNotes(): StateFlow<Resource<List<NoteUIModel>>> = noteGetAllInteractor.invoke()
        .flatMapLatest { flowOf(it.map(listMapper)) }
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Resource.Loading
        )

    suspend fun deleteNotesByIds(ids: List<Int>) = viewModelScope.launch(Dispatchers.IO) {
        noteDeleteByIdsInteractor.invoke(ids)
    }
}