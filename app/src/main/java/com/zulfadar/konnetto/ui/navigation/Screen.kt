package com.zulfadar.konnetto.ui.navigation

sealed class Screen(val route: String) {
    data object LoginPage : Screen("loginPage")
    data object RegisterPage : Screen("registerPage")
    data object HomePage : Screen("homePage")
    data object ProfilePage : Screen("profilPage")
    data object CommunityPage : Screen("communityPage")
    data object CreateNewPostPage : Screen("createNewPostPage")
    data object EventPage : Screen("eventPage")
    data object NotificationPage: Screen("notificationPage")
}