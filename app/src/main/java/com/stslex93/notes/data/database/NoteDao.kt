package com.stslex93.notes.data.database

import androidx.room.*
import com.stslex93.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun getAll(): Flow<List<Note>>

    @Query("SELECT * FROM note_table WHERE id=:id")
    fun getNote(id: Int): Flow<Note>

    @Query("SELECT * FROM note_table WHERE id IN (:ids)")
    fun getNotesById(ids: List<String>): Flow<List<Note>>

    @Query("DELETE FROM note_table WHERE id IN (:ids) ")
    suspend fun deleteNotesById(ids: List<Int>)

    @Insert
    suspend fun insert(note: Note)

    @Insert
    suspend fun insertAll(notes: List<Note>)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(note: Note)

    @Update(entity = Note::class)
    suspend fun update(note: Note)

}