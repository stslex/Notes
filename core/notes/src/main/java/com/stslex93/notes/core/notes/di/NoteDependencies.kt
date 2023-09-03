package com.stslex93.notes.core.notes.di

import com.stslex93.notes.core.database.NoteDao

interface NoteDependencies {

    val noteDao: NoteDao
}