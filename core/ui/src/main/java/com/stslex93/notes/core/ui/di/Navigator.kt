package com.stslex93.notes.core.ui.di

fun interface Navigator {
    operator fun invoke(screen: Screen)
}
