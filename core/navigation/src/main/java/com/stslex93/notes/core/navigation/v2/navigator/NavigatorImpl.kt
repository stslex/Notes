package com.stslex93.notes.core.navigation.v2.navigator

import androidx.navigation.NavOptions
import com.stslex93.notes.core.navigation.v2.controller.NavExtrasHostController
import com.stslex93.notes.core.ui.base.NavigationScreen
import com.stslex93.notes.core.ui.di.Navigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigatorImpl @Inject constructor(
    private val navController: NavExtrasHostController
) : Navigator {

    override fun navigate(screen: NavigationScreen) {

        val options = navController.currentDestination
            ?.route
            ?.takeIf { screen.isSingleTop }
            ?.let { currentRoute ->
                NavOptions.Builder().apply {
                    setPopUpTo(
                        route = currentRoute,
                        inclusive = true,
                        saveState = true
                    )
                    setLaunchSingleTop(true)
                }
            }?.build()

        navController.navigate(
            screen = screen,
            navOptions = options
        )
    }

    override fun popBackStack() {
        navController.popBackStack()
    }
}
