package com.stslex93.notes.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.stslex93.notes.data.database.base.BaseRoomDatabase
import com.stslex93.notes.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 3,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 2,
            to = 3,
            spec = NoteRoomDatabase.NoteAutoMigration::class
        )
    ]
)
abstract class NoteRoomDatabase : RoomDatabase(), BaseRoomDatabase<NoteDao> {

    class NoteAutoMigration : AutoMigrationSpec
}