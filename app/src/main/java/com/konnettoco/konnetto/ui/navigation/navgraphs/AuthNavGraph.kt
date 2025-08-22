package com.konnettoco.konnetto.ui.navigation.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.konnettoco.konnetto.ui.navigation.Screen
import com.konnettoco.konnetto.ui.screen.auth.forgotpassword.ForgotPassWordScreen
import com.konnettoco.konnetto.ui.screen.auth.forgotpassword.NewPassWordScreen
import com.konnettoco.konnetto.ui.screen.auth.login.LoginScreen
import com.konnettoco.konnetto.ui.screen.auth.otppages.OtpScreen
import com.konnettoco.konnetto.ui.screen.auth.register.RegisterScreen

fun NavGraphBuilder.authNavGraph(navController: androidx.navigation.NavHostController) {
    navigation(
        startDestination = Screen.LoginPage.route,
        route = "auth_graph"
    ) {
        composable(Screen.LoginPage.route) {
            LoginScreen(
                onClickToLogin = {
                    navController.navigate(Screen.OtpPage.route) {
                        popUpTo(Screen.LoginPage.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onClickToSignUp = {
                    navController.navigate(Screen.RegisterPage.route)
                },
                onForgotPasswordClick = {
                    navController.navigate(Screen.ForgotPasswordPage.route)
                }
            )
        }

        composable(Screen.RegisterPage.route) {
            RegisterScreen(
                onClickToRegister = {
                    navController.navigate("${Screen.OtpPage.route}?source=login") {
                        popUpTo(Screen.RegisterPage.route) { inclusive = true }
                    }
                },
                navigateToLogin = {
                    navController.navigate(Screen.LoginPage.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = "${Screen.OtpPage.route}?source={source}",
            arguments = listOf(
                navArgument("source") {
                    type = NavType.StringType
                    defaultValue = "login"
                }
            )
        ) { backStackEntry ->
            val source = backStackEntry.arguments?.getString("source") ?: "login"
            OtpScreen(
                onConfirmClick = {
                    if (source == "forgot") {
                        navController.navigate(Screen.NewPasswordPage.route) {
                            popUpTo(Screen.OtpPage.route) { inclusive = true }
                        }
                    } else {
                        navController.navigate("main_graph") {
                            popUpTo(Screen.OtpPage.route) {
                                inclusive = true
                            }
                        }
                    }
                }
            )
        }

        composable(Screen.ForgotPasswordPage.route) {
            ForgotPassWordScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSendClick = {
                    navController.navigate("${Screen.OtpPage.route}?source=forgot")
                }
            )
        }

        composable(Screen.NewPasswordPage.route) {
            NewPassWordScreen(
                onSendClick = {
                    navController.navigate(Screen.LoginPage.route) {
                        popUpTo(Screen.NewPasswordPage.route) { inclusive = true }
                    }
                }
            )
        }
    }
}