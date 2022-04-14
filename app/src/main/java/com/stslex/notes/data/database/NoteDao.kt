package com.stslex.notes.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stslex.notes.data.entity.NoteEntity
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

    @Query("SELECT * FROM note_table WHERE id=:id")
    fun getNote(id: Int): Flow<NoteEntity>

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