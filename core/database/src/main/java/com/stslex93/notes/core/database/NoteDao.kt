package com.stslex93.notes.core.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stslex93.notes.core.database.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query(
        "SELECT * FROM note_table " +
                "WHERE title LIKE '%' || :query || '%' OR " +
                "content LIKE '%' || :query || '%' " +
                "ORDER BY timestamp DESC"
    )
    fun getAll(query: String): PagingSource<Int, NoteEntity>

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): List<NoteEntity>

    @Query("SELECT * FROM note_table WHERE id=:id")
    fun getNote(id: Int): Flow<NoteEntity>

    @Query("SELECT * FROM note_table ORDER BY timestamp DESC LIMIT 1")
    fun getLastNote(): Flow<NoteEntity>

    @Query("SELECT * FROM note_table WHERE id IN (:ids)")
    fun getNotesById(ids: List<String>): Flow<List<NoteEntity>>

    @Query("DELETE FROM note_table WHERE id IN (:ids) ")
    suspend fun deleteNotesById(ids: List<Int>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(note: List<NoteEntity>)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()
}