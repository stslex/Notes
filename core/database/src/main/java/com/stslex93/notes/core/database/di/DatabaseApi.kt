package com.stslex93.notes.core.database.di

import com.stslex93.notes.core.database.label.LabelDao
import com.stslex93.notes.core.database.note.NoteDao

interface DatabaseApi {

    val noteDao: NoteDao

    val labelDao: LabelDao
}