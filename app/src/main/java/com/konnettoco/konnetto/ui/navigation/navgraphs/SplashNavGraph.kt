package com.konnettoco.konnetto.ui.navigation.navgraphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.konnettoco.konnetto.ui.navigation.Screen
import com.konnettoco.konnetto.ui.screen.splash.SplashScreen

fun NavGraphBuilder.splashNavGraph(
    navController: NavController
) {
    navigation(
        startDestination = Screen.SplashPage.route,
        route = "splash_graph"
    ) {
        composable(Screen.SplashPage.route) {
            SplashScreen(
                onFinished = { isLoggedIn ->
                    navController.navigate(
                        if (isLoggedIn) "main_graph" else "auth_graph"
                    ) {
                        popUpTo("splash_graph")
                    }
                },
            )
        }
    }
}