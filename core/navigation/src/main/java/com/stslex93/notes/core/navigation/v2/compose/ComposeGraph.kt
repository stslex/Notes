package com.stslex93.notes.core.navigation.v2.compose

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.createGraph
import androidx.navigation.get
import androidx.navigation.navArgument
import com.stslex93.notes.core.navigation.v2.controller.ComposeNavigator
import com.stslex93.notes.core.navigation.v2.controller.Destination
import com.stslex93.notes.core.navigation.v2.controller.NavExtrasHostController
import com.stslex93.notes.core.navigation.v2.screen.HostScreen

fun NavGraphBuilder.composableArg(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (backStackEntry: NavBackStackEntry, arguments: Bundle?) -> Unit
) {
    addDestination(
        Destination(provider[ComposeNavigator::class], content).apply {
            this.route = route
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}

inline fun <reified T : Parcelable> NavGraphBuilder.composableArg(
    screen: HostScreen,
    crossinline content: @Composable (data: T?) -> Unit
) {
    composableArg(
        route = screen.route,
        arguments = listOf(navArgument(screen.key) {
            NavType.ParcelableType(T::class.java)
        }),
        deepLinks = emptyList()
    ) { _, arguments ->
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(screen.key, T::class.java)
        } else {
            arguments?.getParcelable<T>(screen.key)
        }
        content(data)
    }
}

fun NavGraphBuilder.composable(
    screen: HostScreen,
    content: @Composable (backStackEntry: NavBackStackEntry) -> Unit
) {
    composableArg(
        route = screen.route,
        arguments = emptyList(),
        deepLinks = emptyList()
    ) { backStackEntry, _ ->
        content(backStackEntry)
    }
}

@Composable
fun NavExtrasHost(
    navController: NavExtrasHostController,
    modifier: Modifier = Modifier,
    route: String? = null,
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController,
        remember(route, navController.startDestination.route, builder) {
            navController.createGraph(navController.startDestination.route, route, builder)
        },
        modifier
    )
}