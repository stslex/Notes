package com.stslex93.notes.core.database.di

import com.stslex93.notes.core.database.NoteDao

interface DatabaseApi {

    val dao: NoteDao
}