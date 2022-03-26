package com.stslex.notes.data.database

import androidx.room.*
import com.stslex.notes.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table")
    fun getAll(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_table WHERE id=:id")
    fun getNote(id: Int): Flow<NoteEntity>

    @Query("SELECT * FROM note_table WHERE id IN (:ids)")
    fun getNotesById(ids: List<String>): Flow<List<NoteEntity>>

    @Query("DELETE FROM note_table WHERE id IN (:ids) ")
    suspend fun deleteNotesById(ids: List<Int>)

    @Insert
    suspend fun insert(note: NoteEntity)

    @Insert
    suspend fun insertAll(notes: List<NoteEntity>)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(note: NoteEntity)

    @Update(entity = NoteEntity::class)
    suspend fun update(note: NoteEntity)

}