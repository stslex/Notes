package com.stslex93.notes.core.database.label

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "label_table")
data class LabelEntity(
    @PrimaryKey
    @ColumnInfo(name = "uuid")
    val uuid: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "timestamp")
    val timestamp: String
)