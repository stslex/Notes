package com.stslex93.notes.core.database.di

import android.content.Context
import androidx.room.Room
import com.stslex93.notes.core.database.NoteDao
import com.stslex93.notes.core.database.database.NoteRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDao(context: Context): NoteDao = Room
        .databaseBuilder(
            context,
            NoteRoomDatabase::class.java,
            NoteRoomDatabase.DB_NAME
        )
        .build()
        .dao
}