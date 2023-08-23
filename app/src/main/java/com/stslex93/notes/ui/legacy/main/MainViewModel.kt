package com.stslex93.notes.ui.legacy.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.stslex93.notes.domain.interactor.MainScreenInteractor
import com.stslex93.notes.ui.legacy.model.NoteUIModel
import com.stslex93.notes.ui.legacy.model.toDomain
import com.stslex93.notes.ui.legacy.model.toUI
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val interactor: MainScreenInteractor
) : ViewModel() {

    val notes: StateFlow<PagingData<NoteUIModel>>
        get() = interactor.searchNotes
            .map { pagingData -> pagingData.map { it.toUI() } }
            .cachedIn(viewModelScope)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = PagingData.empty()
            )

    fun setQuery(query: String) {
        interactor.search(query)
    }

    fun deleteNotesByIds(notes: List<NoteUIModel>) {
        viewModelScope.launch {
            val noteList: List<Int> = notes.map { it.id };
            interactor.deleteAll(noteList)
        }
    }

    fun insertAll(notes: List<NoteUIModel>) {
        viewModelScope.launch {
            val data = notes.map { it.toDomain() }
            interactor.insertAll(data)
        }
    }
}