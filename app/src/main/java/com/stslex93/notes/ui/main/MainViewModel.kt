package com.stslex93.notes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex93.notes.core.Resource
import com.stslex93.notes.data.model.NoteListDataUIMapper
import com.stslex93.notes.data.repository.MainScreenRepository
import com.stslex93.notes.ui.model.NoteUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: MainScreenRepository,
    private val listMapper: NoteListDataUIMapper
) : ViewModel() {

    @ExperimentalCoroutinesApi
    suspend fun getAllNotes(): StateFlow<Resource<List<NoteUI>>> = repository.getAllNotes()
        .flatMapLatest { flowOf(it.map(listMapper)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Resource.Loading
        )

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAll()
    }

    fun deleteNotesByIds(ids: List<Int>) = viewModelScope.launch(Dispatchers.IO){
        repository.deleteNotesByIds(ids)
    }
}