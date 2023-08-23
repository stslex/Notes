package com.stslex93.notes.feature.home.domain.model

data class NoteDomain(
    val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long,
)