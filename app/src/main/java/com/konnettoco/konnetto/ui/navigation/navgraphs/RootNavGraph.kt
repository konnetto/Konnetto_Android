package com.konnettoco.konnetto.ui.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavGraph(
    navController: NavHostController,
    drawerState: androidx.compose.material3.DrawerState,
    isLoggedIn: Boolean = true // sementara true biar langsung ke Main
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Graph.MAIN else Graph.AUTH
    ) {
        authNavGraph(navController)
        mainNavGraph(navController, drawerState)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
}