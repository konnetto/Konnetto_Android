package com.konnettoco.konnetto.ui.navigation.navgraphs

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.konnettoco.konnetto.ui.navigation.Screen
import com.konnettoco.konnetto.ui.screen.addnewpost.CreateNewPostScreen
import com.konnettoco.konnetto.ui.screen.friendrequest.FriendRequestScreen
import com.konnettoco.konnetto.ui.screen.home.HomeScreen
import com.konnettoco.konnetto.ui.screen.library.mylibrary.LibraryPageScreen
import com.konnettoco.konnetto.ui.screen.notification.NotificationScreen
import com.konnettoco.konnetto.ui.screen.profile.editprofilescreen.EditProfileScreen
import com.konnettoco.konnetto.ui.screen.profile.friendlist.FriendListScreen
import com.konnettoco.konnetto.ui.screen.profile.shareprofile.ShareProfileScreen
import com.konnettoco.konnetto.ui.screen.profile.userprofile.ProfileScreen
import com.konnettoco.konnetto.ui.screen.saved.SavedPageScreen
import com.konnettoco.konnetto.ui.screen.search.SearchPageScreen
import com.konnettoco.konnetto.ui.screen.settings.SettingsPageScreen
import kotlinx.coroutines.launch

fun NavGraphBuilder.mainNavGraph(
    navController: androidx.navigation.NavHostController,
    drawerState: androidx.compose.material3.DrawerState
) {
    navigation(
        startDestination = Screen.HomePage.route,
        route = "main_graph"
    ) {
        composable(Screen.HomePage.route) {
            val scope = rememberCoroutineScope()
            BackHandler(enabled = true) {
                if (drawerState.isOpen) {
                    scope.launch { drawerState.close() }
                } else {
                    (navController.context as? Activity)?.finish()
                }
            }
            HomeScreen(
                onMenuClick = {
                    scope.launch {
                        if (drawerState.isClosed) drawerState.open() else drawerState.close()
                    }
                },
                onSearchClick = {
                    navController.navigate(Screen.SearchPage.route)
                }
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
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.NotificationPage.route) {
            NotificationScreen(
                onNotificationTileClick = {},
                onSlideToDelete = {},
                onMoreVertClick = {}
            )
        }

        composable(Screen.SavedPage.route) {
            SavedPageScreen(onBackClick = { navController.popBackStack() }, onMoreVertClick = {})
        }

        composable(Screen.LibraryPage.route) {
            LibraryPageScreen(onBackClick = { navController.popBackStack() }, onMoreVertClick = {})
        }

        composable(Screen.FriendRequestPage.route) {
            FriendRequestScreen(
                onBackClick = { navController.popBackStack() },
                onAccaptClick = {},
                onDeclineClick = {},
                onDisplaynameClick = {}
            )
        }

        composable(Screen.SettingsPage.route) {
            SettingsPageScreen(onBackClick = { navController.popBackStack() })
        }

        composable(Screen.ProfilePage.route) {
            ProfileScreen(
                onBackClick = { navController.popBackStack() },
                onShareBtnClick = { navController.navigate(Screen.ShareProfilePage.route) },
                onEdtBtnClick = { navController.navigate(Screen.EditProflePage.route) },
                onFriendCountClick = { navController.navigate(Screen.FriendListPage.route) }
            )
        }

        composable(Screen.EditProflePage.route) {
            EditProfileScreen(onBackClick = { navController.popBackStack() })
        }

        composable(Screen.FriendListPage.route) {
            FriendListScreen(navigateToProfile = {}, onBackClick = { navController.popBackStack() })
        }

        composable(Screen.ShareProfilePage.route) {
            ShareProfileScreen(onBackClick = { navController.popBackStack() })
        }
    }
}