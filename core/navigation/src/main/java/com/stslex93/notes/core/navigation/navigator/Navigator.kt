package com.stslex93.notes.core.navigation.navigator

import st.slex.csplashscreen.core.navigation.NavigationScreen

fun interface Navigator {
    operator fun invoke(screen: NavigationScreen)
}

