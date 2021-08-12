package com.stslex93.notes

import android.app.Application
import com.stslex93.notes.data.repository.NoteRepository
import com.stslex93.notes.data.source.NoteRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { NoteRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}