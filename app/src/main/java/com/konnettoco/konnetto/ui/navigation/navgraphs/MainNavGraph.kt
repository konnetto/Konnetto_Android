package com.konnettoco.konnetto.ui.navigation.navgraphs

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.konnettoco.konnetto.ui.navigation.Screen
import com.konnettoco.konnetto.ui.screen.addnewpost.CreateNewPostScreen
import com.konnettoco.konnetto.ui.screen.discovery.DiscoveryScreen
import com.konnettoco.konnetto.ui.screen.friendrequest.FriendRequestScreen
import com.konnettoco.konnetto.ui.screen.home.HomeScreen
import com.konnettoco.konnetto.ui.screen.library.editprogress.EditProgressScreen
import com.konnettoco.konnetto.ui.screen.library.mylibrary.LibraryPageScreen
import com.konnettoco.konnetto.ui.screen.library.mylibrarydetail.LibraryDetailScreen
import com.konnettoco.konnetto.ui.screen.notification.NotificationScreen
import com.konnettoco.konnetto.ui.screen.profile.editprofilescreen.EditProfileScreen
import com.konnettoco.konnetto.ui.screen.profile.friendlist.FriendListScreen
import com.konnettoco.konnetto.ui.screen.profile.otheruserprofile.OtherUserProfileScreen
import com.konnettoco.konnetto.ui.screen.profile.shareprofile.ShareProfileScreen
import com.konnettoco.konnetto.ui.screen.profile.userprofile.ProfileScreen
import com.konnettoco.konnetto.ui.screen.saved.SavedPageScreen
import com.konnettoco.konnetto.ui.screen.search.SearchPageScreen
import com.konnettoco.konnetto.ui.screen.settings.SettingsPageScreen
import com.konnettoco.konnetto.ui.screen.settings.SettingsViewModel
import com.konnettoco.konnetto.ui.viewModelFactory.SettingsViewModelFactory
import kotlinx.coroutines.launch

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
    drawerState: DrawerState
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
                },
                onDisplaynameClick = { userId ->
                    navController.navigate(Screen.OtherUserProfilePage.createRout(userId))
                } ,
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

        composable(Screen.DiscoveryPage.route) {
            DiscoveryScreen()
        }

        composable(
            route = Screen.OtherUserProfilePage.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            OtherUserProfileScreen(
                onBackClick = { navController.popBackStack() },
                onShareBtnClick = {},
                onAddFrndBtnClick = {},
                onFriendCountClick = {},
                userId = userId.toLong(),
            )
        }

        composable(Screen.SavedPage.route) {
            SavedPageScreen(onBackClick = { navController.popBackStack() }, onMoreVertClick = {})
        }

        composable(Screen.LibraryPage.route) {
            LibraryPageScreen(
                onBackClick = { navController.popBackStack() },
                onMoreVertClick = {},
                onLibraryItemClick = { libraryItemId ->
                    navController.navigate(Screen.LibraryDetailPage.createRoute(libraryItemId))
                },
            )
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
            val viewModel: SettingsViewModel = viewModel(
                factory = SettingsViewModelFactory(LocalContext.current)
            )
            SettingsPageScreen(
                onBackClick = { navController.popBackStack() },
                viewModel = viewModel,
            )
        }

        composable(Screen.ProfilePage.route) {
            ProfileScreen(
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

        composable(
            route = Screen.LibraryDetailPage.route,
            arguments = listOf(navArgument("libraryItemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val libraryItemId = backStackEntry.arguments?.getInt("libraryItemId") ?: 0
            LibraryDetailScreen(
                onBackClick = { navController.popBackStack() },
                libraryItemId = libraryItemId.toLong(),
                onEditButtonClick =  { libraryItemId ->
                    navController.navigate(Screen.EditProgressPage.createRoute(libraryItemId))
                },
            )
        }

        composable(
            route = Screen.EditProgressPage.route,
            arguments = listOf(navArgument("libraryItemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val libraryItemId = backStackEntry.arguments?.getInt("libraryItemId") ?: 0
            EditProgressScreen(
                onBackClick = { navController.popBackStack() },
                libraryItemId = libraryItemId.toLong(),
            )
        }
    }
}