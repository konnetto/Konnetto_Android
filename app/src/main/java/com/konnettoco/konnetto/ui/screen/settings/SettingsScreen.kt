package com.konnettoco.konnetto.ui.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.screen.settings.components.SeparatorTitle
import com.konnettoco.konnetto.ui.screen.settings.components.SettingsCard
import com.konnettoco.konnetto.ui.screen.settings.components.SettingsCardMultipleContent

@Composable
fun SettingsPageScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: SettingsViewModel
) {
    val isDarkTheme by viewModel.isDarkTheme.collectAsState(initial = false)

    SettingsPageContent(
        modifier = modifier,
        onBackClick = onBackClick,
        darkThemeChecked = isDarkTheme,
        onDarkThemeToggle = { viewModel.toggleTheme(it) }
    )
}

@Composable
fun SettingsPageContent(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    darkThemeChecked: Boolean,
    onDarkThemeToggle: (Boolean) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SettingsPageTopAppBar(
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            SeparatorTitle(
                icon = R.drawable.baseline_darkmode,
                title = "Appearence"
            )
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                colors = CardDefaults.cardColors(
                    MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.elevatedCardElevation(1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        modifier = Modifier.padding(
                            end = 90.dp
                        )
                    ){
                        Text(
                            text = "Dark Mode",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Switch to dark theme",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Spacer(modifier = modifier.width(60.dp))
                    Switch(
                        checked = darkThemeChecked,
                        onCheckedChange = { onDarkThemeToggle(it) },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colorScheme.primary,
                            checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            SeparatorTitle(
                icon = R.drawable.baseline_notifications_24,
                title = "Notifications"
            )
            Spacer(modifier = Modifier.height(12.dp))
            SettingsCard(
                title = "Notifications",
                desrciption = "Manage your notification preferences",
                icon = R.drawable.baseline_keyboard_arrow_right,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(12.dp))
            SeparatorTitle(
                icon = R.drawable.baseline_language_24,
                title = "Policy & Language"
            )
            Spacer(modifier = Modifier.height(12.dp))
            SettingsCardMultipleContent(
                title = "App Language",
                title2 = "Privacy Policy",
                title3 = "Terms of Use",
                desrciption = "Manage App Language",
                desrciption2 = "How we Pprotect your data",
                desrciption3 = "Legal terms and conditions",
                icon = R.drawable.baseline_keyboard_arrow_right,
                icon2 = R.drawable.baseline_keyboard_arrow_right,
                icon3 = R.drawable.baseline_keyboard_arrow_right,
                onItem1Click = {},
                onItem2Click = {},
                onItem3Click = {},
            )
            Spacer(modifier = Modifier.height(12.dp))
            SeparatorTitle(
                icon = R.drawable.baseline_help_center_24,
                title = "Help Center"
            )
            Spacer(modifier = Modifier.height(12.dp))
            SettingsCardMultipleContent(
                title = "Report a Bug",
                desrciption = "Help us improve Konnetto",
                title2 = "Request a Feature",
                desrciption2 = "Suggest new Feature",
                title3 = "Contact Support",
                desrciption3 = "Get help from our team",
                icon = R.drawable.baseline_keyboard_arrow_right,
                icon2 = R.drawable.baseline_keyboard_arrow_right,
                icon3 = R.drawable.baseline_keyboard_arrow_right,
                onItem1Click = {},
                onItem2Click = {},
                onItem3Click = {},
            )
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 18.dp),
                colors = CardDefaults.cardColors(
                    MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.elevatedCardElevation(1.dp)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {  }
                        .padding(horizontal = 20.dp, vertical = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_logout_24),
                        contentDescription = "Log out",
                        tint = Color.Red
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Log out",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
                }
            Spacer(modifier = Modifier.height(80.dp))
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    .border(
                        width = 4.dp,
                        color = MaterialTheme.colorScheme.background,
                        shape = CircleShape
                    )
                    .padding(3.dp)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.background)
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Konnetto",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Version 1.0.0",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Powered by WiBer",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SettingsPageTopAppBar(
    onBackClick: () -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back),
                    contentDescription = "back button"
                )
            }
        },
        title = {
            Text("Settings",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
        },
    )
}

//@Preview(showBackground = true)
//@Composable
//private fun EventPageScreenPreview() {
//    KonnettoTheme {
//        SettingsPageContent(
//            onBackClick = { },
//            darkThemeChecked = {},
//            onDarkThemeToggle = {}
//        )
//    }
//}