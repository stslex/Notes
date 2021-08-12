package com.stslex93.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stslex93.notes.data.repository.NoteRepository

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