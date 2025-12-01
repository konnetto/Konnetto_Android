package com.konnettoco.konnetto.ui.navigation.navgraphs

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(
    navController: NavHostController,
    drawerState: DrawerState,
//    isLoggedIn: Boolean
) {
//    val startDestination = if (isLoggedIn) "main_graph" else "auth_graph"

    NavHost(
        navController = navController,
        startDestination = "splash_graph",
        modifier = Modifier
    ) {
        splashNavGraph(navController = navController)
        authNavGraph(navController = navController)
        mainNavGraph(
            navController = navController,
            drawerState = drawerState
        )
    }
}
