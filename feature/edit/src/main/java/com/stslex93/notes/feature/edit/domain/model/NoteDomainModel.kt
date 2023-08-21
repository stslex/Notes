package com.stslex93.notes.feature.edit.domain.model

data class NoteDomainModel(
    val id: Int,
    val title: String,
    val content: String,
    val timestamp: Long,
)