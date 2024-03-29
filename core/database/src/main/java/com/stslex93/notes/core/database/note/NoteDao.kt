package com.stslex93.notes.core.database.note

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
    fun getNoteFlow(id: Int): Flow<NoteEntity>

    @Query("SELECT * FROM note_table WHERE id=:id")
    suspend fun getNote(id: Int): NoteEntity

    @Query("SELECT * FROM note_table WHERE id IN (:ids)")
    fun getNotesById(ids: List<String>): Flow<List<NoteEntity>>

    @Query("DELETE FROM note_table WHERE id IN (:ids) ")
    suspend fun deleteNotesById(ids: List<Int>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()
}