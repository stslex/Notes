package com.stslex93.notes.core.navigation.utils

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.stslex93.notes.core.navigation.model.AppDestination

object NavExt {

    val AppDestination.composableArguments: List<NamedNavArgument>
        get() = argsNames.map { name ->
            navArgument(name) { NavType.StringType }
        }

    val AppDestination.parseArguments: NavBackStackEntry.() -> List<String>
        get() = {
            argsNames.map { name ->
                arguments?.getString(name).orEmpty()
            }
        }

    fun findByRoute(route: String?) = if (route == null) {
        AppDestination.UNDEFINED
    } else {
        AppDestination
            .entries
            .firstOrNull { destination ->
                destination.navigationRoute == route
            }
    }
}