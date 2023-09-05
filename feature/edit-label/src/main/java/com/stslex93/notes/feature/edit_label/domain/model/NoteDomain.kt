package com.stslex93.notes.feature.edit_label.domain.model

data class NoteDomain(
    val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long,
    val labels: Set<LabelDomain>
)