package com.stslex93.notes.data.database

import com.stslex93.notes.data.database.base.BaseRoomCallback
import com.stslex93.notes.data.database.base.BaseRoomDatabase
import dagger.Lazy
import kotlinx.coroutines.CoroutineScope

class NoteDatabaseCallback(
    scope: CoroutineScope,
    noteRoomDatabase: Lazy<BaseRoomDatabase<NoteDao>>
) : BaseRoomCallback<NoteDao>(scope, noteRoomDatabase) {
    override suspend fun populateDatabase(dao: NoteDao) = dao.deleteAll()
}