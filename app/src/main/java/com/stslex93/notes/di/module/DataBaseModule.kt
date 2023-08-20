package com.stslex93.notes.di.module

import android.content.Context
import androidx.paging.PagingConfig
import androidx.room.Room
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.database.NoteRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        context: Context,
    ): NoteRoomDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            NoteRoomDatabase::class.java,
            DATABASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideForecastDao(db: NoteRoomDatabase): NoteDao = db.dao()

    @Provides
    fun providesPagingConfig(): PagingConfig =
        PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false)


    companion object {
        private const val PAGE_SIZE = 100
        private const val DATABASE_NAME: String = "note_database"
    }
}