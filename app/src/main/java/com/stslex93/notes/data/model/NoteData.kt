package com.stslex93.notes.data.model

import com.stslex93.notes.data.entity.NoteEntity

interface NoteData {

    fun id(): Int
    fun title(): String
    fun content(): String
    fun timestamp(): Long
    fun mapToEntity(mapper: NoteDataEntityMapper): NoteEntity

    data class Base(
        private val id: Int = 0,
        private val title: String,
        private val content: String,
        private val timestamp: Long,
    ) : NoteData {

        override fun id(): Int = id
        override fun title(): String = title
        override fun content(): String = content
        override fun timestamp(): Long = timestamp
        override fun mapToEntity(mapper: NoteDataEntityMapper): NoteEntity = mapper.map(this)
    }
}