package com.stslex.notes.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.core.Resource
import com.stslex.notes.domain.interactor.interf.NoteGetSingleInteractor
import com.stslex.notes.domain.interactor.interf.NoteInsertSingleInteractor
import com.stslex.notes.domain.interactor.interf.NoteUpdateSingleInteractor
import com.stslex.notes.ui.mapper.NoteDomainUIMapper
import com.stslex.notes.ui.mapper.NoteUIDomainMapper
import com.stslex.notes.ui.model.NoteUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditNoteViewModel @Inject constructor(
    private val noteGetSingleInteractor: NoteGetSingleInteractor,
    private val noteInsertSingleInteractor: NoteInsertSingleInteractor,
    private val noteUpdateSingleInteractor: NoteUpdateSingleInteractor,
    private val mapperDomainUI: NoteDomainUIMapper,
    private val mapperUIDomain: NoteUIDomainMapper
) : ViewModel() {

    @ExperimentalCoroutinesApi
    fun getNoteById(id: Int): StateFlow<Resource<NoteUIModel>> = noteGetSingleInteractor.invoke(id)
        .flatMapLatest { flowOf(it.map(mapperDomainUI)) }
        .flowOn(Dispatchers.IO)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Resource.Loading
        )

    fun insertNote(note: NoteUIModel) = viewModelScope.launch(Dispatchers.IO) {
        noteInsertSingleInteractor.invoke(mapperUIDomain.map(data = note))
    }

    fun updateNote(note: NoteUIModel) = viewModelScope.launch(Dispatchers.IO) {
        noteUpdateSingleInteractor.invoke(mapperUIDomain.map(data = note))
    }
}