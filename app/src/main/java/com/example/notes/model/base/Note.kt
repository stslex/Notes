package com.example.notes.model.base

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "note_table")
data class Note(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        @ColumnInfo(name = "title") val title: String,
        @ColumnInfo(name = "content") val content: String,
        @ColumnInfo(name = "datestamp") val datestamp: String,
        @ColumnInfo(name = "timestamp") val timestamp: String,
) : Parcelable