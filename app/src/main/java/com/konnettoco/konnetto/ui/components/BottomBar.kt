package com.konnettoco.konnetto.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.navigation.NavigationItem
import com.konnettoco.konnetto.ui.navigation.Screen

@Composable
fun BottomBar(
    navController: NavHostController,
    currentRoute: String?,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .height(70.dp)
//            .padding(top = 40.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .windowInsetsBottomHeight(
                WindowInsets.navigationBars.add(WindowInsets(bottom = 56.dp))
            ),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        windowInsets = NavigationBarDefaults.windowInsets,
        tonalElevation = 12.dp

    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = painterResource(R.drawable.icons8_home),
                screen = Screen.HomePage
            ),
            NavigationItem(
                title = "Discovery",
                icon = painterResource(R.drawable.baseline_discover_24),
                screen = Screen.DiscoveryPage
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
            NavigationItem(
                title = "Profile",
                icon = painterResource(R.drawable.baseline_person_24),
                screen = Screen.ProfilePage
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
                            modifier = Modifier.aspectRatio(0.6f)
                        )
                    }
                },
//                label = { Text(
//                    text = item.title,
//                    fontSize = 12.sp,
//                    fontWeight = FontWeight.Thin,
//                    color = MaterialTheme.colorScheme.primary
//                ) },
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
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}