package com.stslex93.notes.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex93.notes.core.Resource
import com.stslex93.notes.data.model.NoteDataUIMapper
import com.stslex93.notes.domain.interactor.interf.NoteGetSingleInteractor
import com.stslex93.notes.domain.interactor.interf.NoteInsertSingleInteractor
import com.stslex93.notes.domain.interactor.interf.NoteUpdateSingleInteractor
import com.stslex93.notes.ui.model.NoteUI
import com.stslex93.notes.ui.model.NoteUIDataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditNoteViewModel @Inject constructor(
    private val noteGetSingleInteractor: NoteGetSingleInteractor,
    private val noteInsertSingleInteractor: NoteInsertSingleInteractor,
    private val noteUpdateSingleInteractor: NoteUpdateSingleInteractor,
    private val mapperData: NoteDataUIMapper,
    private val mapperUI: NoteUIDataMapper
) : ViewModel() {

    @ExperimentalCoroutinesApi
    fun getNoteById(id: Int): StateFlow<Resource<NoteUI>> =
        noteGetSingleInteractor.invoke(id).flatMapLatest { flowOf(it.map(mapperData)) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = Resource.Loading
            )

    fun insertNote(note: NoteUI) = viewModelScope.launch(Dispatchers.IO) {
        noteInsertSingleInteractor.invoke(note.mapToData(mapperUI))
    }

    fun updateNote(note: NoteUI) = viewModelScope.launch(Dispatchers.IO) {
        noteUpdateSingleInteractor.invoke(note.mapToData(mapperUI))
    }
}