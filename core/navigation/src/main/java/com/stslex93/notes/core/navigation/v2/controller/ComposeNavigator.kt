package com.stslex93.notes.core.navigation.v2.controller

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.Navigator

@Navigator.Name("composable")
class ComposeNavigator : Navigator<Destination>() {

    override fun createDestination(): Destination = Destination(this) { _, _ -> }

    fun onTransitionComplete(entry: NavBackStackEntry) {
        state.markTransitionComplete(entry)
    }

    internal companion object {
        const val NAME = "composable"
    }
}

@NavDestination.ClassType(Composable::class)
class Destination(
    navigator: ComposeNavigator,
    internal val content: @Composable (backStackEntry: NavBackStackEntry, arguments: Bundle?) -> Unit
) : NavDestination(navigator)