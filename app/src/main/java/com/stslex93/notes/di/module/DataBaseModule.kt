package com.stslex93.notes.di.module

import android.content.Context
import androidx.paging.PagingConfig
import androidx.room.Room
import com.stslex93.notes.data.database.NoteDao
import com.stslex93.notes.data.database.NoteDatabaseCallback
import com.stslex93.notes.data.database.NoteRoomDatabase
import com.stslex93.notes.data.database.base.BaseRoomCallback
import com.stslex93.notes.data.database.base.BaseRoomDatabase
import dagger.Lazy
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        context: Context,
        roomCallback: BaseRoomCallback<NoteDao>
    ): BaseRoomDatabase<NoteDao> = Room.databaseBuilder(
        context.applicationContext,
        NoteRoomDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration()
        .addCallback(roomCallback)
        .build()

    @Singleton
    @Provides
    fun provideForecastDao(db: BaseRoomDatabase<NoteDao>): NoteDao = db.dao()

    @Provides
    fun providesNoteRoomCallback(roomDatabase: Lazy<BaseRoomDatabase<NoteDao>>): BaseRoomCallback<NoteDao> =
        NoteDatabaseCallback(CoroutineScope(SupervisorJob()), roomDatabase)

    @Provides
    fun providesPagingConfig(): PagingConfig =
        PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false)


    companion object {
        private const val PAGE_SIZE = 100
        private const val DATABASE_NAME: String = "note_database"
    }
}