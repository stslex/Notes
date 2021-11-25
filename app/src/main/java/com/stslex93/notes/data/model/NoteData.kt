package com.stslex93.notes.data.model

interface NoteData {

    fun id(): String
    fun title(): String
    fun content(): String
    fun datestamp(): String
    fun timestamp(): String

    data class Base(
        private val id: Int = 0,
        private val title: String,
        private val content: String,
        private val datestamp: String,
        private val timestamp: String,
    ) : NoteData{

        override fun id(): String = id.toString()
        override fun title(): String = title
        override fun content(): String = content
        override fun datestamp(): String = datestamp
        override fun timestamp(): String = timestamp
    }
}