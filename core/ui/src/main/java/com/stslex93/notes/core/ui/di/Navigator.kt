package com.stslex93.notes.core.ui.di

import com.stslex93.notes.core.ui.base.NavigationScreen

interface Navigator {

    fun navigate(screen: NavigationScreen)

    fun popBackStack()
}
