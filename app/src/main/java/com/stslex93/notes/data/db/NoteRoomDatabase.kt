package com.stslex93.notes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stslex93.notes.data.entity.Note

@Database(entities = [Note::class], version = 3, exportSchema = false)
abstract class NoteRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}