package com.stslex93.notes.core.database.di

import android.content.Context
import androidx.room.Room
import com.stslex93.notes.core.database.note.NoteDao
import com.stslex93.notes.core.database.database.NoteRoomDatabase
import com.stslex93.notes.core.database.label.LabelDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Context): NoteRoomDatabase = NoteRoomDatabase.build(context)

    @Provides
    @Singleton
    fun provideNoteDao(database: NoteRoomDatabase): NoteDao = database.noteDao

    @Provides
    @Singleton
    fun provideLabelDao(database: NoteRoomDatabase): LabelDao = database.labelDao
}