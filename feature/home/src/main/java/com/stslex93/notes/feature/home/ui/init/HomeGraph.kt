package com.stslex93.notes.feature.home.ui.init

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex93.notes.core.navigation.AppDestination
import com.stslex93.notes.feature.home.ui.HomeViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
) {
    composable(
        route = AppDestination.HOME.navigationRoute
    ) {
        val viewModel = koinViewModel<HomeViewModel>()
        HomeScreeInit(
            modifier = modifier,
            viewModel = viewModel
        )
    }
}