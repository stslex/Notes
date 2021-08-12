package com.stslex93.notes.di.module

import android.app.Application
import com.stslex93.notes.data.db.NoteDao
import com.stslex93.notes.data.db.NoteRoomDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabase(application: Application): NoteRoomDatabase =
        NoteRoomDatabase.getDatabase(
            application.applicationContext,
            CoroutineScope(SupervisorJob())
        )

    @Singleton
    @Provides
    fun provideForecastDao(db: NoteRoomDatabase): NoteDao = db.noteDao()
}