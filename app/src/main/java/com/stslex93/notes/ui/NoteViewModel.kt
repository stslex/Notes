package com.stslex93.notes.ui

import androidx.lifecycle.*
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.data.repository.NoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private var _allNotes = MutableLiveData<List<Note>>()
    val allNotes: LiveData<List<Note>> get() = _allNotes
    fun getAll() = viewModelScope.launch {
        _allNotes = repository.getAll().asLiveData() as MutableLiveData<List<Note>>
    }

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun insertAll(notes: List<Note>) = viewModelScope.launch {
        repository.insertAll(notes)
    }

    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun deleteNotes(notes: List<Note>) = viewModelScope.launch {
        repository.deleteNotes(notes)
    }
}