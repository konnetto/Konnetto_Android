package com.zulfadar.konnetto

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zulfadar.konnetto.ui.navigation.NavigationItem
import com.zulfadar.konnetto.ui.navigation.Screen
import com.zulfadar.konnetto.ui.screen.addnewpost.CreateNewPostScreen
import com.zulfadar.konnetto.ui.screen.friendrequest.FriendRequestScreen
import com.zulfadar.konnetto.ui.screen.home.HomeScreen
import com.zulfadar.konnetto.ui.screen.library.LibraryPageScreen
import com.zulfadar.konnetto.ui.screen.login.LoginScreen
import com.zulfadar.konnetto.ui.screen.notification.NotificationScreen
import com.zulfadar.konnetto.ui.screen.profile.ProfileScreen
import com.zulfadar.konnetto.ui.screen.register.RegisterScreen
import com.zulfadar.konnetto.ui.screen.saved.SavedPageScreen
import com.zulfadar.konnetto.ui.screen.settings.SettingsPageScreen
import com.zulfadar.konnetto.ui.theme.KonnettoTheme
import kotlinx.coroutines.launch

@Composable
fun KonnettoApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
// Drawer content hanya aktif di HomePage
    val isHomePage = currentRoute == Screen.HomePage.route

    Box(modifier = modifier.fillMaxSize()) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                if (isHomePage) {
                    ModalDrawerSheet(
                        modifier = Modifier.widthIn(max = 270.dp),
                        drawerContainerColor = DrawerDefaults.containerColor
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
                                navController.navigate(Screen.ProfilePage.route) {
                                    launchSingleTop = true
                                }
                                scope.launch { drawerState.close() }
                            }
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
                                navController.navigate(Screen.SavedPage.route) {
                                    launchSingleTop = true
                                }
                                scope.launch { drawerState.close() }
                            }
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
                                navController.navigate(Screen.FriendRequestPage.route) {
                                    launchSingleTop = true
                                }
                                scope.launch { drawerState.close() }
                            }
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
                                navController.navigate(Screen.LibraryPage.route) {
                                    launchSingleTop = true
                                }
                                scope.launch { drawerState.close() }
                            }
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
                                navController.navigate(Screen.SettingsPage.route) {
                                    launchSingleTop = true
                                }
                                scope.launch { drawerState.close() }
                            }
                        )
                    }
                }
//                else {
//                    // Kosong atau konten drawer minimal untuk mencegah buka drawer di luar home
//                    Spacer(modifier = Modifier.height(1.dp))
//                }
            },
            modifier = Modifier.widthIn(min = 30.dp)
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.HomePage.route,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(Screen.LoginPage.route) {
                    LoginScreen(
                        onClickToLogin = {
                            navController.navigate(Screen.HomePage.route) {
                                popUpTo(Screen.LoginPage.route) { inclusive = true }
                                launchSingleTop = true
                            }
                        },
                        onClickToSignUp = {
                            navController.navigate(Screen.RegisterPage.route) {
                                launchSingleTop = true
                            }
                        }
                    )
                }

                composable(Screen.RegisterPage.route) {
                    RegisterScreen(
                        onClickToRegister = {
                            navController.navigate(Screen.LoginPage.route) {
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

                composable(Screen.HomePage.route) {
                    BackHandler(enabled = true) {
                        if (drawerState.isOpen) {
                            scope.launch { drawerState.close() }
                        } else {
                            (navController.context as? Activity)?.finish()
                        }
                    }

                    HomeScreen(
                        navigateToComment = {},
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
                        onProfileClick = {
                            navController.navigate(Screen.ProfilePage.route) {
                                launchSingleTop = true
                            }
                        },
                        onSearchClick = {}
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
                    SavedPageScreen()
                }

                composable(Screen.LibraryPage.route) {
                    LibraryPageScreen()
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
                        onCommentClick = {},
                    )
                }
            }

            if (shouldShowBottomBar(currentRoute)) {
                BottomBar(
                    navController = navController,
                    currentRoute = currentRoute,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
//            if (currentRoute in listOf(
//                    Screen.HomePage.route,
//                    Screen.CreateNewPostPage.route,
//                    Screen.NotificationPage.route,
//                )
//            ) {
//                BottomBar(
//                    navController = navController,
//                    currentRoute = currentRoute,
//                    modifier = Modifier.align(Alignment.BottomCenter)
//                )
//            }
        }
    }
}

private fun shouldShowBottomBar(currentRoute: String?): Boolean {
    return currentRoute !in listOf(
        Screen.LoginPage.route,
        Screen.RegisterPage.route,
        Screen.CreateNewPostPage.route,
        Screen.ProfilePage.route,
        Screen.SavedPage.route,
        Screen.FriendRequestPage.route,
        Screen.LibraryPage.route,
        Screen.SettingsPage.route
    )
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    currentRoute: String?,
    modifier: Modifier = Modifier
) {
//    val navigationItems = remember {
//        listOf(
//            NavigationItem(
//                "Home",
//                Icons.Outlined.Home,
//                Screen.HomePage
//            ),
////            NavigationItem("Community", Icons.Outlined.AccountCircle, Screen.CommunityPage),
//            NavigationItem(
//                "Add",
//                Icons.Outlined.Add,
//                Screen.CreateNewPostPage
//            ),
////            NavigationItem("Event", Icons.Outlined.DateRange, Screen.EventPage),
//            NavigationItem(
//                "Notification",
//                Icons.Outlined.Notifications,
//                Screen.NotificationPage
//            ),
//        )
//    }

//    NavigationBar(
//        containerColor = MaterialTheme.colorScheme.background,
//        modifier = modifier
//            .heightIn(max = 100.dp)
//            .navigationBarsPadding() // ini menyesuaikan dengan navigation bar sistem
//            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
//    ) {
//        navigationItems.forEach { item ->
//            val selected = currentRoute == item.screen.route
//            NavigationBarItem(
//                icon = {
//                    Icon(
//                        imageVector = item.icon,
//                        contentDescription = item.title,
//                        modifier = Modifier.size(30.dp),
//                        tint = if (selected) MaterialTheme.colorScheme.primary else Color.Gray
//                    )
//                },
//                selected = selected,
//                onClick = {
//                    if (!selected) {
//                        navController.navigate(item.screen.route) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                },
//                colors = NavigationBarItemDefaults.colors(
//                    indicatorColor = Color.Transparent
//                )
//            )
//        }
//    }
    NavigationBar(
        modifier = modifier
            .padding(top = 40.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .windowInsetsBottomHeight(
                WindowInsets.navigationBars.add(WindowInsets(bottom = 56.dp))
            ),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        windowInsets = NavigationBarDefaults.windowInsets,
        tonalElevation = 12.dp

    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = painterResource(R.drawable.icons8_home),
                screen = Screen.HomePage
            ),
            NavigationItem(
                title = "Add New Post",
                icon = painterResource(R.drawable.add_square_icon),
                screen = Screen.CreateNewPostPage
            ),
            NavigationItem(
                title = "Notification",
                icon = painterResource(R.drawable.icons_notification),
                screen = Screen.NotificationPage
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                },
//                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.LightGray,
                    indicatorColor = Color.Transparent
                ),
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
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