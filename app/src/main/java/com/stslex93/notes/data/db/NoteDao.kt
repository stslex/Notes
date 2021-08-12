package com.stslex93.notes.data.db

import androidx.room.*
import com.stslex93.notes.data.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun getAll(): Flow<List<Note>>

    @Insert
    suspend fun insert(note: Note)

    @Insert
    suspend fun insertAll(notes: List<Note>)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(note: Note)

    @Delete
    suspend fun deleteNotes(notes: List<Note>)

    @Update(entity = Note::class)
    suspend fun update(note: Note)

}