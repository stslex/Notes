package com.stslex93.notes.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.stslex93.notes.data.database.base.BaseRoomDatabase
import com.stslex93.notes.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 5,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 3,
            to = 4,
            spec = NoteRoomDatabase.NoteAutoMigration::class
        ),
        AutoMigration(
            from = 3,
            to = 5,
            spec = NoteRoomDatabase.NoteAutoMigration::class
        ),
        AutoMigration(
            from = 4,
            to = 5,
            spec = NoteRoomDatabase.NoteAutoMigration::class
        )
    ]
)
abstract class NoteRoomDatabase : RoomDatabase(), BaseRoomDatabase<NoteDao> {

    class NoteAutoMigration : AutoMigrationSpec
}