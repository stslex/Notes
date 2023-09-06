package com.stslex93.notes.core.navigation.v2.navigator

import com.stslex93.notes.core.navigation.v2.controller.NavExtrasHostController
import com.stslex93.notes.core.ui.base.NavigationScreen
import com.stslex93.notes.core.ui.di.Navigator
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
    private val navController: NavExtrasHostController
) : Navigator {

    override fun navigate(screen: NavigationScreen) {
        navController.navigate(screen = screen) {
            navController.currentDestination
                ?.route
                ?.takeIf { screen.isSingleTop }
                ?.let { currentRoute ->
                    popUpTo(currentRoute) {
                        inclusive = true
                        saveState = true
                    }
                    launchSingleTop = true
                }
        }
    }

    override fun popBackStack() {
        navController.popBackStack()
    }
}
