package com.stslex93.notes.core.navigation.v2.screen

enum class HostScreen {
    HOME,
    NOTE_EDIT;

    val route: String = name.lowercase()
    val key: String = "${name.lowercase()}_key"
}