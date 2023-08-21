package com.stslex93.notes.data.model

data class NoteDataModel(
    val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long,
) {

    companion object {

        val EMPTY
            get() = NoteDataModel(
                id = 0,
                title = "",
                content = "",
                timestamp = System.currentTimeMillis()
            )
    }
}