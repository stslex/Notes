package com.stslex93.notes

import androidx.lifecycle.*
import com.stslex93.notes.model.NoteRepository
import com.stslex93.notes.model.base.Note
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    var theme: MutableLiveData<Boolean> = repository.theme

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

class NoteViewModelFactory(private val repository: NoteRepository) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}