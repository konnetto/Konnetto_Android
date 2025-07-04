package com.zulfadar.konnetto.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
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
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.ui.navigation.NavigationItem
import com.zulfadar.konnetto.ui.navigation.Screen

@Composable
fun BottomBar(
    navController: NavHostController,
    currentRoute: String?,
    modifier: Modifier = Modifier
) {
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
                            modifier = Modifier.aspectRatio(0.6f)
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