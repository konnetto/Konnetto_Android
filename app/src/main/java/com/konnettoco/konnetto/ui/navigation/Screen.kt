package com.konnettoco.konnetto.ui.navigation

sealed class Screen(val route: String) {
    data object LoginPage : Screen("loginPage")
    data object RegisterPage : Screen("registerPage")
    data object ForgotPasswordPage : Screen("forgotPasswordPage")
    data object NewPasswordPage : Screen("newPasswordPage")
    data object OtpPage : Screen("otpPage")
    data object HomePage : Screen("homePage")
    data object SearchPage : Screen("searchPage")
    data object ProfilePage : Screen("profilPage")
    data object EditProflePage : Screen("editProfilePage")
    data object ShareProfilePage : Screen("shareProfilePage")
    data object FriendListPage : Screen("friendListPage")
    data object CommunityPage : Screen("communityPage")
    data object CreateNewPostPage : Screen("createNewPostPage")
    data object EventPage : Screen("eventPage")
    data object NotificationPage: Screen("notificationPage")
    data object SavedPage: Screen("savedPage")
    data object FriendRequestPage: Screen("friendRequestPage")
    data object LibraryPage: Screen("libraryPage")
    data object SettingsPage: Screen("settingsPage")

    //Detail
    data object LibraryDetailPage: Screen("libraryDetailPage/{libraryItemId}") {
        fun createRoute(libraryItemId: Long) = "libraryDetailPage/$libraryItemId"
    }
}