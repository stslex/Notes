package com.stslex93.notes.core.navigation.v2.compose

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.get
import com.stslex93.notes.core.navigation.v2.controller.ComposeNavigator
import com.stslex93.notes.core.navigation.v2.controller.Destination
import com.stslex93.notes.core.navigation.v2.screen.HostScreen

fun NavGraphBuilder.composable(
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

inline fun <reified T : Parcelable> NavGraphBuilder.composable(
    screen: HostScreen,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    crossinline content: @Composable (backStackEntry: NavBackStackEntry, arguments: T?) -> Unit
) {
    composable(
        route = screen.route,
        arguments = arguments,
        deepLinks = deepLinks,
    ) { backStackEntry, args ->
        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            args?.getParcelable(screen.key, T::class.java)
        } else {
            args?.getParcelable(screen.key)
        }
        content(backStackEntry, data)
    }
}

inline fun <reified T : Parcelable> NavGraphBuilder.composable(
    screen: HostScreen,
    crossinline content: @Composable (data: T) -> Unit
) {
    composable<T>(screen) { _, arguments ->
        arguments?.let { data ->
            content(data)
        }
    }
}
