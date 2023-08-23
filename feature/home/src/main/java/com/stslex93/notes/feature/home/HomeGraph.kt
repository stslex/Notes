package com.stslex93.notes.feature.home

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex93.notes.core.navigation.AppDestination

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.HOME.navigationRoute
    ) {
    }
}