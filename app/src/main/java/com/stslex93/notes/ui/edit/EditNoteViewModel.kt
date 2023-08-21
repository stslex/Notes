package com.stslex93.notes.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex93.notes.domain.interactor.interf.NoteEditInteractor
import com.stslex93.notes.ui.model.NoteUIModel
import com.stslex93.notes.ui.model.mapper.toDomain
import com.stslex93.notes.ui.model.mapper.toUI
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class EditNoteViewModel @Inject constructor(
    private val interactor: NoteEditInteractor
) : ViewModel() {

    val note: StateFlow<NoteUIModel> = interactor
        .note
        .map {
            it.toUI()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            NoteUIModel.EMPTY
        )

    fun getNoteById(id: Int) {
        viewModelScope.launch {
            interactor.getNote(id)
        }
    }

    fun insertNote(note: NoteUIModel) {
        viewModelScope.launch {
            interactor.insert(note.toDomain())
        }
    }
}