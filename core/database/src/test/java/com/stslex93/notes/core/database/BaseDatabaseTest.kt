package com.stslex93.notes.core.database

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.stslex93.notes.core.database.database.NoteRoomDatabase

internal abstract class BaseDatabaseTest {

    protected lateinit var database: NoteRoomDatabase

    open fun initDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, NoteRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    open fun clearDb() {
        database.close()
    }

    class TestApplication : Application()

}