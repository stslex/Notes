package com.stslex93.notes.di.module

import android.content.Context
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.database.NoteRoomDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): NoteRoomDatabase =
        NoteRoomDatabase.getDatabase(context, CoroutineScope(SupervisorJob()))

    @Singleton
    @Provides
    fun provideForecastDao(db: NoteRoomDatabase): NoteDao = db.noteDao()
}