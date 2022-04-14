package com.stslex93.notes.domain.model

interface NoteDomainModel {

    fun id(): Int
    fun title(): String
    fun content(): String
    fun timestamp(): Long

    data class Base(
        private val id: Int,
        private val title: String,
        private val content: String,
        private val timestamp: Long,
    ) : NoteDomainModel {

        override fun id(): Int = id
        override fun title(): String = title
        override fun content(): String = content
        override fun timestamp(): Long = timestamp
    }
}