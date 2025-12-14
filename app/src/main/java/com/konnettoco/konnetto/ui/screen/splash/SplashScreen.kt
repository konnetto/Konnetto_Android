package com.konnettoco.konnetto.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konnettoco.konnetto.R
import com.konnettoco.konnetto.ui.screen.settings.SettingsViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onFinished: (Boolean) -> Unit,
    splashViewModel: SplashViewModel = hiltViewModel(),
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {
    val isLoggedIn = splashViewModel.isLoggedIn.collectAsState(initial = null).value
    val isThemeLoading = settingsViewModel.isLoading.collectAsState(initial = false).value

    LaunchedEffect(isLoggedIn, isThemeLoading) {
        if (isLoggedIn != null && !isThemeLoading) {
            onFinished(isLoggedIn)
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.splash_logo),
                contentDescription = "Logo",
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp),
            )
        }

        Text(
            text = "Konnetto",
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF00FF7F), // Hijau
                        Color(0xFF8A2BE2), // Ungu
                        Color(0xFF1E90FF)  // Biru
                    )
                )
            ),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun SplashScreenPreview() {
//    KonnettoTheme {
//        SplashScreen()
//    }
//}