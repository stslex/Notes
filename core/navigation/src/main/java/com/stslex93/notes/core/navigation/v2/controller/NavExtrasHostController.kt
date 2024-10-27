package com.stslex93.notes.core.navigation.v2.controller

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.navigation.compose.DialogNavigator
import com.stslex93.notes.core.navigation.v2.screen.Home
import com.stslex93.notes.core.ui.base.NavigationScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavExtrasHostController(
    context: Context,
    val startDestination: NavigationScreen
) : NavHostController(context) {

    private val _currentScreensBackStack: MutableStateFlow<MutableMap<String, NavigationScreen>> =
        MutableStateFlow(mutableMapOf(startDestination.route to startDestination))

    val currentScreensBackStack: StateFlow<Map<String, NavigationScreen>> =
        _currentScreensBackStack.asStateFlow()

    override fun popBackStack(): Boolean {
        _currentScreensBackStack.update { screensMap ->
            screensMap.apply { remove(currentDestination?.route) }
        }
        return super.popBackStack()
    }

    fun navigate(screen: NavigationScreen, navOptions: NavOptions? = null) {
        _currentScreensBackStack.update { it.apply { put(screen.route, screen) } }
        navigate(route = screen.route, navOptions = navOptions)
    }

    fun navigate(screen: NavigationScreen, builder: NavOptionsBuilder.() -> Unit) {
        navigate(route = screen.route, builder = builder)
    }
}

private fun NavExtrasControllerSaver(
    context: Context,
    startDestination: NavigationScreen
): Saver<NavExtrasHostController, *> = Saver(
    save = { it.saveState() },
    restore = { createNavExtrasController(context, startDestination).apply { restoreState(it) } }
)

private fun createNavExtrasController(
    context: Context,
    startDestination: NavigationScreen
) = NavExtrasHostController(
    context = context,
    startDestination = startDestination
).apply {
    navigatorProvider.addNavigator(ComposeNavigator())
    navigatorProvider.addNavigator(DialogNavigator())
}

@Composable
fun rememberNavExtrasController(
    startDestination: NavigationScreen = Home,
    vararg navigators: Navigator<out NavDestination>
): NavExtrasHostController {
    val context = LocalContext.current
    return rememberSaveable(
        inputs = navigators,
        saver = NavExtrasControllerSaver(context, startDestination)
    ) {
        createNavExtrasController(context, startDestination)
    }.apply {
        for (navigator in navigators) {
            navigatorProvider.addNavigator(navigator)
        }
    }
}