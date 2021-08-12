package com.stslex93.notes.di.module

import com.stslex93.notes.data.db.NoteDao
import com.stslex93.notes.data.db.NoteRoomDataBaseImpl
import com.stslex93.notes.data.db.NoteRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabase(): NoteRoomDatabase =
        NoteRoomDataBaseImpl().noteRoomDatabase

    @Singleton
    @Provides
    fun provideForecastDao(db: NoteRoomDatabase): NoteDao = db.noteDao()
}