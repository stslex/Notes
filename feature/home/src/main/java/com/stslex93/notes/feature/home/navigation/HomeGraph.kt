package com.stslex93.notes.feature.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.stslex93.notes.core.navigation.v2.compose.composable
import com.stslex93.notes.core.navigation.v2.screen.HostScreen
import com.stslex93.notes.feature.home.di.setupComponent
import com.stslex93.notes.feature.home.ui.init.HomeScreeInit

fun NavGraphBuilder.homeGraph(
    modifier: Modifier = Modifier,
) {
    composable(HostScreen.HOME.route) { _,  _ ->
        HomeScreeInit(
            modifier = modifier,
            viewModel = setupComponent()
        )
    }
}