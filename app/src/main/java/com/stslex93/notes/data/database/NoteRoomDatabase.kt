package com.stslex93.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stslex93.notes.data.database.base.BaseRoomDatabase
import com.stslex93.notes.data.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 4, exportSchema = false)
abstract class NoteRoomDatabase : RoomDatabase(), BaseRoomDatabase<NoteDao>