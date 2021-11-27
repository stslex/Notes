package com.stslex93.notes.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex93.notes.core.Resource
import com.stslex93.notes.data.model.NoteDataUIMapper
import com.stslex93.notes.data.repository.EditNoteRepository
import com.stslex93.notes.ui.model.NoteUI
import com.stslex93.notes.ui.model.NoteUIDataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditNoteViewModel @Inject constructor(
    private val repository: EditNoteRepository,
    private val mapperData: NoteDataUIMapper,
    private val mapperUI: NoteUIDataMapper
) : ViewModel() {

    @ExperimentalCoroutinesApi
    suspend fun getNoteById(id: Int): StateFlow<Resource<NoteUI>> = repository.getNoteById(id)
        .flatMapLatest { flowOf(it.map(mapperData)) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Resource.Loading
        )

    fun insertNote(note: NoteUI) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNote(note.mapToData(mapperUI))
    }

    fun updateNote(note: NoteUI) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(note.mapToData(mapperUI))
    }
}