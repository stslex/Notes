package com.stslex93.notes.core.database.label

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LabelDao {

    @Query("SELECT * FROM label_table WHERE uuid = :uuid LIMIT 1")
    fun getLabel(uuid: String): LabelEntity

    @Query("SELECT * FROM label_table WHERE uuid in (:uuids)")
    fun getLabels(uuids: Set<String>): Set<LabelEntity>

    @Query("SELECT * FROM label_table WHERE uuid in (:uuids)")
    fun getMatchLabels(uuids: List<String>): Flow<List<LabelEntity>>

    @Query(
        "SELECT * FROM label_table " +
                "WHERE title LIKE '%' || :query || '%'" +
                "ORDER BY timestamp DESC"
    )
    fun search(query: String): PagingSource<Int, LabelEntity>

    @Query("SELECT * FROM label_table ORDER BY timestamp DESC")
    fun getAll(): PagingSource<Int, LabelEntity>

    @Query("DELETE from label_table WHERE uuid = :uuid")
    suspend fun removeLabel(uuid: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLabel(label: LabelEntity)
}