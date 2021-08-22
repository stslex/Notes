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
    fun getNoteById(id: String): LiveData<Note> = repository.getNote(id = id).asLiveData()
    fun getNotesByIds(ids: List<String>) = repository.getNotesById(ids = ids).asLiveData()

    fun deleteNotesByIds(ids: List<String>) = viewModelScope.launch {
        repository.deleteNotesById(ids = ids)
    }

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note = note)
    }

    fun insertAll(notes: List<Note>) = viewModelScope.launch {
        repository.insertAll(notes = notes)
    }

    fun update(note: Note) = viewModelScope.launch {
        repository.update(note = note)
    }
}
