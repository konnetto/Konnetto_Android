package com.zulfadar.konnetto

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zulfadar.konnetto.ui.navigation.NavigationItem
import com.zulfadar.konnetto.ui.navigation.Screen
import com.zulfadar.konnetto.ui.screen.CreateNewPost.CreateNewPostScreen
import com.zulfadar.konnetto.ui.screen.community.CommunityScreen
import com.zulfadar.konnetto.ui.screen.eventpage.EventPageScreen
import com.zulfadar.konnetto.ui.screen.home.HomeScreen
import com.zulfadar.konnetto.ui.screen.login.LoginScreen
import com.zulfadar.konnetto.ui.screen.notification.NotificationScreen
import com.zulfadar.konnetto.ui.screen.profile.ProfileScreen
import com.zulfadar.konnetto.ui.screen.register.RegisterScreen
import com.zulfadar.konnetto.ui.theme.KonnettoTheme

@Composable
fun KonnettoApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            when (currentRoute) {
                Screen.LoginPage.route, Screen.RegisterPage.route, Screen.ProfilePage.route -> Unit
                else -> BottomBar(navController)
            }
        },
        contentWindowInsets = WindowInsets(0.dp),
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomePage.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.LoginPage.route) {
                LoginScreen(
                    onClickToLogin = {
                        navController.navigate(Screen.HomePage.route)
                    },
                    onClickToSignUp = {
                        navController.navigate(Screen.RegisterPage.route)
                    }
                )
            }
            composable(Screen.RegisterPage.route) {
                RegisterScreen(
                    onClickToRegister = {
                        navController.navigate(Screen.LoginPage.route)
                    },
                    navigateToLogin = {
                        navController.navigate(Screen.LoginPage.route)
                    },
                )
            }
            composable(Screen.HomePage.route) {
                HomeScreen(
                    navigateToComment = {},
                    onMenuClick = {},
                    onProfileClick = {
                        navController.navigate(Screen.ProfilePage.route)
                    }
                )
            }
            composable(Screen.CommunityPage.route) {
                CommunityScreen()
            }
            composable(Screen.CreateNewPostPage.route) {
                CreateNewPostScreen()
            }
            composable(Screen.EventPage.route) {
                EventPageScreen()
            }
            composable(Screen.NotificationPage.route) {
                NotificationScreen()
            }
            composable(Screen.ProfilePage.route) {
                ProfileScreen(
                    onBackClick = {
                        navController.navigate(Screen.HomePage.route)
                    },
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
            .height(70.dp)
            .padding(bottom = 8.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Outlined.Home,
                screen = Screen.HomePage,
            ),
            NavigationItem(
                title = "Community",
                icon = Icons.Outlined.AccountCircle,
                screen = Screen.CommunityPage
            ),
            NavigationItem(
                title = "Add",
                icon = Icons.Outlined.Add,
                screen = Screen.CreateNewPostPage
            ),
            NavigationItem(
                title = "Event",
                icon = Icons.Outlined.DateRange,
                screen = Screen.EventPage
            ),
            NavigationItem(
                title = "Notification",
                icon = Icons.Outlined.Notifications,
                screen = Screen.NotificationPage
            ),
        )
        navigationItems.map { item ->
            val selected = currentRoute == item.screen.route
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(30.dp),
                        tint = if (selected) MaterialTheme.colorScheme.primary else Color.Gray
                    )
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.SpaceEvenly
//                    ) {
//                        Icon(
//                            imageVector = item.icon,
//                            contentDescription = item.title,
//                            modifier = Modifier.size(30.dp),
//                            tint = if (selected) MaterialTheme.colorScheme.primary else Color.Gray
//                        )
//                        Text(
//                            text = item.title,
//                            fontSize = 12.sp,
//                            color = if (selected) MaterialTheme.colorScheme.primary else Color.Gray
//                        )
//                    }
                },
//                label = { Text(item.title) },
                selected = selected,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}



@Preview
@Composable
private fun KonnettoAppPreview() {
    KonnettoTheme {
        KonnettoApp()
    }
}