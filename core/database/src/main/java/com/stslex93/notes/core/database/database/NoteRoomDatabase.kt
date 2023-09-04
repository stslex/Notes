package com.stslex93.notes.core.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stslex93.notes.core.database.database.AutoMigrations.AUTO_MIGRATION_1_2
import com.stslex93.notes.core.database.database.AutoMigrations.AUTO_MIGRATION_2_3
import com.stslex93.notes.core.database.database.AutoMigrations.AUTO_MIGRATION_3_4
import com.stslex93.notes.core.database.database.AutoMigrations.AUTO_MIGRATION_4_5
import com.stslex93.notes.core.database.database.AutoMigrations.AUTO_MIGRATION_5_6
import com.stslex93.notes.core.database.label.LabelDao
import com.stslex93.notes.core.database.label.LabelEntity
import com.stslex93.notes.core.database.note.NoteDao
import com.stslex93.notes.core.database.note.NoteEntity

@Database(
    entities = [
        NoteEntity::class,
        LabelEntity::class
    ],
    version = 6,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao
    abstract val labelDao: LabelDao

    companion object {
        private const val DB_NAME = "db.note"

        fun build(
            context: Context
        ) = Room
            .databaseBuilder(
                context,
                NoteRoomDatabase::class.java,
                DB_NAME
            )
            .addAutoMigrationSpec(AUTO_MIGRATION_1_2)
            .addAutoMigrationSpec(AUTO_MIGRATION_2_3)
            .addAutoMigrationSpec(AUTO_MIGRATION_3_4)
            .addAutoMigrationSpec(AUTO_MIGRATION_4_5)
            .addAutoMigrationSpec(AUTO_MIGRATION_5_6)
            .build()
    }
}
