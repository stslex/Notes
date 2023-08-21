package com.stslex93.notes.ui.edit.model

data class Note(
    val uuid: Int,
    val title: String,
    val content: String,
    val timestamp: Long
)
