package com.stslex93.notes.core.notes.model

data class NoteDataModel(
    val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long,
    val labelUuids: Set<String>
)