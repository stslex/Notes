package com.example.notes

import android.app.Application
import com.example.notes.model.NoteRepository
import com.example.notes.model.database.NoteRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { NoteRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}