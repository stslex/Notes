package com.stslex93.notes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.stslex93.notes.data.model.Note
import com.stslex93.notes.data.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val allNotes: LiveData<List<Note>> = repository.allNotes.asLiveData()

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun insertAll(notes: List<Note>) = viewModelScope.launch {
        repository.insertAll(notes)
    }

    fun update(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun delete(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

    fun deleteNotes(notes: List<Note>) = viewModelScope.launch {
        repository.deleteNotes(notes)
    }
}