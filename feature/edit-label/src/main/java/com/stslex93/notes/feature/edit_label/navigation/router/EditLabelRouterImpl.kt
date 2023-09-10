package com.stslex93.notes.feature.edit_label.navigation.router

import com.stslex93.notes.core.navigation.model.NavigationScreen
import com.stslex93.notes.core.ui.di.Navigator
import javax.inject.Inject

class EditLabelRouterImpl @Inject constructor(
    private val navigator: Navigator
) : EditLabelRouter {

    override fun popBackStack() {
        navigator.invoke(NavigationScreen.PopBackStack)
    }
}