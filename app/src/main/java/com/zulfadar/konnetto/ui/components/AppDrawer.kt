package com.zulfadar.konnetto.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.zulfadar.konnetto.R
import com.zulfadar.konnetto.ui.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun AppDrawer(navController: NavHostController, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
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
                navController.navigate(Screen.SavedPage.route) {
                    launchSingleTop = true
                }
                scope.launch { drawerState.close() }
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
                navController.navigate(Screen.FriendRequestPage.route) {
                    launchSingleTop = true
                }
                scope.launch { drawerState.close() }
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
                navController.navigate(Screen.LibraryPage.route) {
                    launchSingleTop = true
                }
                scope.launch { drawerState.close() }
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
                navController.navigate(Screen.SettingsPage.route) {
                    launchSingleTop = true
                }
                scope.launch { drawerState.close() }
            },
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            )
        )
    }
}