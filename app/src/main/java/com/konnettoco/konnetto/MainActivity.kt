package com.konnettoco.konnetto

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.konnettoco.konnetto.ui.KonnettoApp
import com.konnettoco.konnetto.ui.theme.KonnettoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

//        val splashScreen = installSplashScreen()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            //Settings
//            val viewModel: SettingsViewModel = hiltViewModel()
//            val isDarkTheme by viewModel.isDarkTheme.collectAsState(initial = false)
//
//            // login check
//            val splashScreenViewModel: SplashViewModel = hiltViewModel()
//            val isLoggedIn by splashScreenViewModel.isLoggedIn.collectAsState(initial = null)
//
//            splashScreen.setKeepOnScreenCondition {
//                viewModel.isLoading.value || isLoggedIn == null
//            }
//
//            KonnettoTheme(darkTheme = isDarkTheme)  {
//                // A surface container using the background color from theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    if (isLoggedIn != null) {
//                        KonnettoApp(isLoggedIn = isLoggedIn!!)
//                    }
//                }
//            }
            KonnettoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                     KonnettoApp()
                }
            }
        }
    }
}
