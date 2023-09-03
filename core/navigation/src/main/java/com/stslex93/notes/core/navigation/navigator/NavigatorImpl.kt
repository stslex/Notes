package com.stslex93.notes.core.navigation.navigator

import androidx.navigation.NavHostController
import com.stslex.aproselection.core.core.Logger
import com.stslex93.notes.core.navigation.model.NavigationScreen
import com.stslex93.notes.core.ui.di.Navigator
import com.stslex93.notes.core.ui.di.Screen
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
    private val navController: NavHostController
) : Navigator {

    override fun invoke(screen: Screen) {
        when (screen) {
            is NavigationScreen.PopBackStack -> navController.popBackStack()
            is NavigationScreen -> navigateScreen(screen)
            else -> {
                Logger.debug("unresolve navigation route", this::class.simpleName)
            }
        }
    }

    private fun navigateScreen(screen: NavigationScreen) {
        val currentRoute = navController.currentDestination?.route ?: return
        if (currentRoute == screen.screen.navigationRoute) return

        navController.navigate(screen.screenRoute) {
            if (screen.isSingleTop.not()) return@navigate

            popUpTo(currentRoute) {
                inclusive = true
                saveState = true
            }
            launchSingleTop = true
        }
    }
}