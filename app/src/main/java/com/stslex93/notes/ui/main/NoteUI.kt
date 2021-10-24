package com.stslex93.notes.ui.main

interface NoteUI {

    data class Base(
        private val id: Int,
        private val title: String,
        private val content: String,
        private val datestamp: String,
        private val timestamp: String
    ) : NoteUI {

    }
}