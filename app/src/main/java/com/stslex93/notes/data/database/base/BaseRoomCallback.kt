package com.stslex93.notes.data.database.base

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Lazy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseRoomCallback<T>(
    private val scope: CoroutineScope,
    private val roomDatabase: Lazy<BaseRoomDatabase<T>>
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        scope.launch(Dispatchers.IO) { populateDatabase(roomDatabase.get().dao()) }
    }

    abstract suspend fun populateDatabase(dao: T)
}