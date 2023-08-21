package com.stslex93.notes.core.database

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreDatabaseModule = module {
    single<NoteDao> {
        Room
            .databaseBuilder(
                androidContext(),
                NoteRoomDatabase::class.java,
                NoteRoomDatabase.DB_NAME
            )
            .build()
            .dao
    }
}