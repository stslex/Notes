package com.stslex93.notes.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stslex93.notes.core.navigation.v2.compose.NavExtrasHost
import com.stslex93.notes.core.navigation.v2.controller.NavExtrasHostController
import com.stslex93.notes.feature.edit.ui.init.editGraph
import com.stslex93.notes.feature.edit_label.navigation.graph.editLabelGraph
import com.stslex93.notes.feature.home.navigation.homeGraph

@Composable
fun NavigationHost(
    navController: NavExtrasHostController,
    modifier: Modifier = Modifier,
) {
    Text(text = "text")
    NavExtrasHost(
        navController = navController,
    ) {
        homeGraph(modifier)
        editGraph(modifier)
        editLabelGraph(modifier)
    }
}
