package com.stslex93.notes.core.notes.di

import com.stslex93.notes.core.notes.repository.NoteRepository

interface NoteApi {

    val repository: NoteRepository
}