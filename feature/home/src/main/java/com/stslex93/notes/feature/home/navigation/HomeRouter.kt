package com.stslex93.notes.feature.home.navigation

interface HomeRouter {

    fun navToEditNote(id: Int)

    fun navToCreateNote()

    fun navToEditLabel(noteIds: Set<Int>)
}