package com.stslex93.notes.core.navigation.navigator

//class NavigatorImpl @Inject constructor(
//    private val navController: NavHostController
//) : Navigator {
//
//    override fun invoke(screen: Screen) {
//        when (screen) {
//            is NavigationScreen.PopBackStack -> navController.popBackStack()
//            is NavigationScreen -> navigateScreen(screen)
//            else -> {
//                Logger.debug("unresolve navigation route", this::class.simpleName)
//            }
//        }
//    }
//
//    private fun navigateScreen(screen: NavigationScreen) {
//        val currentRoute = navController.currentDestination?.route ?: return
//        if (currentRoute == screen.screen.navigationRoute) return
//
//        navController.navigate(screen.screenRoute) {
//            if (screen.isSingleTop.not()) return@navigate
//
//            popUpTo(currentRoute) {
//                inclusive = true
//                saveState = true
//            }
//            launchSingleTop = true
//        }
//    }
//}