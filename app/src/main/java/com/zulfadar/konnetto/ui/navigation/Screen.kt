package com.zulfadar.konnetto.ui.navigation

sealed class Screen(val route: String, val showBottomBar: Boolean = false) {
    data object LoginPage : Screen("loginPage")
    data object RegisterPage : Screen("registerPage")
    data object HomePage : Screen("homePage", showBottomBar = true)
    data object ProfilePage : Screen("profilPage")
    data object EditProflePage : Screen("editProfilePage")
    data object FriendListPage : Screen("friendListPage")
    data object CommunityPage : Screen("communityPage")
    data object CreateNewPostPage : Screen("createNewPostPage", showBottomBar = true)
    data object EventPage : Screen("eventPage")
    data object NotificationPage: Screen("notificationPage", showBottomBar = true)
    data object SavedPage: Screen("savedPage")
    data object FriendRequestPage: Screen("friendRequestPage")
    data object LibraryPage: Screen("libraryPage")
    data object SettingsPage: Screen("settingsPage")
}