package com.stslex93.notes.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.stslex93.notes.data.database.NoteRoomDatabase.Companion.SCHEMA_VERSION
import com.stslex93.notes.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = SCHEMA_VERSION,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = SCHEMA_VERSION - 3,
            to = SCHEMA_VERSION - 2,
            spec = NoteRoomDatabase.NoteAutoMigration::class
        ),
        AutoMigration(
            from = SCHEMA_VERSION - 2,
            to = SCHEMA_VERSION - 1,
            spec = NoteRoomDatabase.NoteAutoMigration::class
        ),
        AutoMigration(
            from = SCHEMA_VERSION - 1,
            to = SCHEMA_VERSION,
            spec = NoteRoomDatabase.NoteAutoMigration::class
        ),
    ]
)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract val dao: NoteDao

    class NoteAutoMigration : AutoMigrationSpec

    companion object {
        const val SCHEMA_VERSION: Int = 5
        const val DB_NAME = "db.note"
    }
}