package com.stslex93.notes.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.stslex93.notes.data.database.NoteRoomDatabase.Companion.SCHEMA_VERSION
import com.stslex93.notes.data.database.base.BaseRoomDatabase
import com.stslex93.notes.data.database.migration.NoteAutoMigration
import com.stslex93.notes.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = SCHEMA_VERSION,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = SCHEMA_VERSION - 3, to = SCHEMA_VERSION - 2, NoteAutoMigration::class),
        AutoMigration(from = SCHEMA_VERSION - 2, to = SCHEMA_VERSION - 1, NoteAutoMigration::class),
        AutoMigration(from = SCHEMA_VERSION - 1, to = SCHEMA_VERSION, NoteAutoMigration::class),
    ]
)
abstract class NoteRoomDatabase : RoomDatabase(), BaseRoomDatabase<NoteDao> {

    companion object {
        const val SCHEMA_VERSION: Int = 5
    }
}