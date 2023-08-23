package com.stslex93.notes.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stslex93.notes.core.navigation.AppDestination
import com.stslex93.notes.feature.edit.ui.init.editGraph
import com.stslex93.notes.feature.home.ui.init.homeGraph

@Composable
fun NavigationHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: AppDestination = AppDestination.HOME
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route
    ) {
        homeGraph(modifier)
        editGraph(modifier)
    }
}