package com.stslex93.notes.feature.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex93.notes.core.navigation.model.AppDestination
import com.stslex93.notes.feature.home.di.setupComponent
import com.stslex93.notes.feature.home.ui.init.HomeScreeInit

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.HOME.navigationRoute
    ) {
        HomeScreeInit(
            modifier = modifier,
            viewModel = setupComponent()
        )
    }
}