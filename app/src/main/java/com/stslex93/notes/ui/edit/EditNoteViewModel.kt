package com.stslex93.notes.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex93.core.Mapper
import com.stslex93.core.Resource
import com.stslex93.notes.domain.interactor.interf.NoteGetSingleInteractor
import com.stslex93.notes.domain.interactor.interf.NoteInsertSingleInteractor
import com.stslex93.notes.domain.model.NoteDomainModel
import com.stslex93.notes.ui.model.NoteUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditNoteViewModel @Inject constructor(
    private val noteGetSingleInteractor: NoteGetSingleInteractor,
    private val noteInsertInteractor: NoteInsertSingleInteractor,
    private val mapperDomainUI: Mapper.DataToUI<NoteDomainModel, Resource<NoteUIModel>>,
    private val mapperUIDomain: Mapper.Data<NoteUIModel, NoteDomainModel>
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
        noteInsertInteractor.invoke(mapperUIDomain.map(data = note))
    }
}