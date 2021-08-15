package com.stslex93.notes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.stslex93.notes.data.entity.Note
import com.stslex93.notes.data.repository.NoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    val allNotes: LiveData<List<Note>> = repository.getAll().asLiveData()

    fun note(id: String): LiveData<Note> = repository.getNote(id = id).asLiveData()

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