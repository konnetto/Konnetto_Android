package com.konnettoco.konnetto

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.konnettoco.konnetto.ui.components.BottomBar
import com.konnettoco.konnetto.ui.navigation.Screen
import com.konnettoco.konnetto.ui.screen.addnewpost.CreateNewPostScreen
import com.konnettoco.konnetto.ui.screen.auth.forgotpassword.ForgotPassWordScreen
import com.konnettoco.konnetto.ui.screen.auth.forgotpassword.NewPassWordScreen
import com.konnettoco.konnetto.ui.screen.auth.login.LoginScreen
import com.konnettoco.konnetto.ui.screen.auth.otppages.OtpScreen
import com.konnettoco.konnetto.ui.screen.auth.register.RegisterScreen
import com.konnettoco.konnetto.ui.screen.profile.editprofilescreen.EditProfileScreen
import com.konnettoco.konnetto.ui.screen.friendrequest.FriendRequestScreen
import com.konnettoco.konnetto.ui.screen.home.HomeScreen
import com.konnettoco.konnetto.ui.screen.library.mylibrary.LibraryPageScreen
import com.konnettoco.konnetto.ui.screen.notification.NotificationScreen
import com.konnettoco.konnetto.ui.screen.profile.friendlist.FriendListScreen
import com.konnettoco.konnetto.ui.screen.profile.userprofile.ProfileScreen
import com.konnettoco.konnetto.ui.screen.saved.SavedPageScreen
import com.konnettoco.konnetto.ui.screen.search.SearchPageScreen
import com.konnettoco.konnetto.ui.screen.settings.SettingsPageScreen
import kotlinx.coroutines.launch

@Composable
fun KonnettoApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val shouldShowBottomBar = listOf(
        Screen.HomePage.route,
        Screen.NotificationPage.route
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
// Drawer content hanya aktif di HomePage
    val isHomePage = currentRoute == Screen.HomePage.route

    Box(modifier = modifier.fillMaxSize()) {
        ModalNavigationDrawer(
            gesturesEnabled = drawerState.isOpen,
            drawerState = drawerState,
            drawerContent = {
                if (isHomePage) {
                    ModalDrawerSheet(
                        modifier = Modifier.widthIn(max = 270.dp),
                        drawerContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
                        drawerContentColor = MaterialTheme.colorScheme.surfaceContainerLowest
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 14.dp, end = 12.dp, bottom = 20.dp, top = 20.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.logo),
                                contentDescription = "profile picture",
                                modifier = Modifier
                                    .padding(start = 12.dp, end = 4.dp)
                                    .size(78.dp)
                                    .clip(CircleShape)
                            )
                            Column(
                                modifier = Modifier
                                    .padding(20.dp)
                            ) {
                                Text(
                                    text = "displayname",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "username",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Gray
                                )
                            }
                        }
                        Spacer(Modifier.heightIn(min = 20.dp))
                        HorizontalDivider()
                        NavigationDrawerItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.outline_profile),
                                    contentDescription = "profile",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text="Profile",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp
                                )
                            },
                            selected = false,
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                    navController.navigate(Screen.ProfilePage.route) {
                                        launchSingleTop = true
                                    }
                                }
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Transparent
                            )
                        )
                        NavigationDrawerItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.saved_icon),
                                    contentDescription = "Saved",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text="Saved",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp
                                )
                            },
                            selected = false,
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                    navController.navigate(Screen.SavedPage.route) {
                                        launchSingleTop = true
                                    }
                                }
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Transparent
                            )
                        )
                        NavigationDrawerItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.friend_request_icon),
                                    contentDescription = "Friend Request",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text="Friend Request",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp
                                )
                            },
                            selected = false,
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                    navController.navigate(Screen.FriendRequestPage.route) {
                                        launchSingleTop = true
                                    }
                                }
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Transparent
                            )
                        )
                        NavigationDrawerItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.library_icon),
                                    contentDescription = "library",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text="Library",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp
                                )
                            },
                            selected = false,
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                    navController.navigate(Screen.LibraryPage.route) {
                                        launchSingleTop = true
                                    }
                                }
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Transparent
                            )
                        )
                        NavigationDrawerItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.outline_settings_icon),
                                    contentDescription = "Settings",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text="Settings",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp
                                )
                            },
                            selected = false,
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                    navController.navigate(Screen.SettingsPage.route) {
                                        launchSingleTop = true
                                    }
                                }
                            },
                            colors = NavigationDrawerItemDefaults.colors(
                                unselectedContainerColor = Color.Transparent
                            )
                        )
                    }
                }
            },
            modifier = Modifier.widthIn(min = 30.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.LoginPage.route,
                    modifier = Modifier.fillMaxSize()
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
                                navController.navigate(Screen.RegisterPage.route) {
                                    launchSingleTop = true
                                }
                            },
                            onForgotPasswordClick = {
                                navController.navigate(Screen.ForgotPasswordPage.route) {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }

                    composable(Screen.RegisterPage.route) {
                        RegisterScreen(
                            onClickToRegister = {
                                navController.navigate("${Screen.OtpPage.route}?source=login") {
                                    popUpTo(Screen.RegisterPage.route) { inclusive = true }
                                    launchSingleTop = true
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
                                defaultValue = "login" // fallback jika tidak dikirim
                            }
                        )
                    ) { backStackEntry ->
                        val source = backStackEntry.arguments?.getString("source") ?: "login"
                        OtpScreen(
                            onConfirmClick = {
                                if (source == "forgot") {
                                    navController.navigate(Screen.NewPasswordPage.route) {
                                        popUpTo(Screen.OtpPage.route) { inclusive = true }
                                        launchSingleTop = true
                                    }
                                } else {
                                    navController.navigate(Screen.HomePage.route) {
                                        popUpTo(Screen.LoginPage.route) {
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
                                navController.navigate(Screen.LoginPage.route) {
                                    popUpTo(Screen.ForgotPasswordPage.route) { inclusive = true }
                                    launchSingleTop = true
                                }
                            },
                            onSendClick = {
                                navController.navigate("${Screen.OtpPage.route}?source=forgot") {
                                    launchSingleTop = true
                                }
                            }
                        )
                    }

                    composable(Screen.NewPasswordPage.route) {
                        NewPassWordScreen(
                            onSendClick = {
                                navController.navigate(Screen.LoginPage.route) {
                                    popUpTo(Screen.NewPasswordPage.route) { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                        )
                    }

                    composable(Screen.HomePage.route) {
                        BackHandler(enabled = true) {
                            if (drawerState.isOpen) {
                                scope.launch { drawerState.close() }
                            } else {
                                (navController.context as? Activity)?.finish()
                            }
                        }

                        HomeScreen(
                            onMenuClick = {
                                // Hanya buka drawer jika di homePage
                                if (isHomePage) {
                                    scope.launch {
                                        if (drawerState.isClosed) {
                                            drawerState.open()
                                        } else {
                                            drawerState.close()
                                        }
                                    }
                                }
                            },
                            onSearchClick = {
                                navController.navigate(Screen.SearchPage.route) {
                                    launchSingleTop = true
                                }
                            },
                        )
                    }

                    composable(Screen.SearchPage.route) {
                        SearchPageScreen(
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(Screen.CreateNewPostPage.route) {
                        CreateNewPostScreen(
                            onPostButtonClick = {},
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable(Screen.NotificationPage.route) {
                        NotificationScreen(
                            onNotificationTileClick = {},
                            onSlideToDelete = {},
                            onMoreVertClick = {},
                        )
                    }

                    composable(Screen.SavedPage.route) {
                        SavedPageScreen(
                            onBackClick = { navController.popBackStack() },
                            onMoreVertClick = {}
                        )
                    }

                    composable(Screen.LibraryPage.route) {
                        LibraryPageScreen(
                            onBackClick = { navController.popBackStack() },
                            onMoreVertClick = {}
                        )
                    }

                    composable(Screen.FriendRequestPage.route) {
                        FriendRequestScreen(
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onAccaptClick = {},
                            onDeclineClick = {},
                            onDisplaynameClick = {},
                        )
                    }

                    composable(Screen.SettingsPage.route) {
                        SettingsPageScreen(
                            onBackClick = { navController.popBackStack() }
                        )
                    }

                    composable(Screen.ProfilePage.route) {
                        ProfileScreen(
                            onBackClick = { navController.popBackStack() },
                            onShareBtnClick = {},
                            onEdtBtnClick = {
                                navController.navigate(Screen.EditProflePage.route) {
                                    launchSingleTop = true
                                }
                            },
                            onFriendCountClick = {
                                navController.navigate(Screen.FriendListPage.route) {
                                    launchSingleTop = true
                                }
                            },
                        )
                    }
                    composable(Screen.EditProflePage.route) {
                        EditProfileScreen(
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                    composable(Screen.FriendListPage.route) {
                        FriendListScreen(
                            navigateToProfile = {},
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
                if (currentRoute in shouldShowBottomBar) {
                    BottomBar(
                        navController = navController,
                        currentRoute = currentRoute,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            }
        }
    }
}


//@Preview
//@Composable
//private fun KonnettoAppPreview() {
//    KonnettoTheme {
//        KonnettoApp()
//    }
//}